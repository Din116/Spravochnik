package ru.din.presentation.docs

import android.Manifest
import android.arch.lifecycle.Observer
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.docs_adapter_cell.view.*
import kotlinx.android.synthetic.main.fragment_docs.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject
import ru.din.presentation.R
import ru.din.presentation.common.ImageLoader
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class DocsFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
  private val TAG = "DocsFragment"

  private val imageLoader: ImageLoader by inject()
  private val viewModel by viewModel<DocsViewModel>()
  private lateinit var docsAdapter: DocsAdapter
  private lateinit var recyclerView: RecyclerView
  private lateinit var layoutManager: RecyclerView.LayoutManager
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (savedInstanceState == null) {
      viewModel.getDocs(false)
    }
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel.viewState.observe(this, Observer {
      if (it != null) handleViewState(it)
    })
    viewModel.errorState.observe(this, Observer { throwable ->
      throwable?.let {
        Log.e(TAG, "DocsFragment onActivityCreated errorState", throwable)
        Toast.makeText(activity, throwable.message, Toast.LENGTH_LONG).show()
      }
    })
  }

  private fun handleViewState(state: DocsViewState) {
    swipe_container.isRefreshing = state.showLoading
    state.docs?.let { docsAdapter.addDoc(it) }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return layoutInflater.inflate(R.layout.fragment_docs, container, false)
  }

  private val MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: Int = 999

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    Log.e(TAG, "onRequestPermissionsResult requestCode: $Int permissions: $permissions grantResults: $grantResults")
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val execute = false
    if (ContextCompat.checkSelfPermission(this@DocsFragment.requireActivity(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED) {

      // Permission is not granted
      // Should we show an explanation?
      if (ActivityCompat.shouldShowRequestPermissionRationale(this@DocsFragment.requireActivity(),
              Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
        // Show an explanation to the user *asynchronously* -- don't block
        // this thread waiting for the user's response! After the user
        // sees the explanation, try again to request the permission.
      } else {
        // No explanation needed, we can request the permission.
        ActivityCompat.requestPermissions(this@DocsFragment.requireActivity(),
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE)

        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
        // app-defined int constant. The callback method gets the
        // result of the request.
      }
    } else {
      // Permission has already been granted
    }
    docsAdapter = DocsAdapter(imageLoader) { doc, view ->
      Log.e(TAG, "Выбран элемент с номером ${doc.id}")
      Log.e(TAG, "view $view")
      Log.e(TAG, "view id  ${view.docCellTitle.text}")
      Toast.makeText(activity, "Выбран элемент с номером ${doc.id} ${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)}", Toast.LENGTH_SHORT).show()
      try {
        doc.url?.let {
          AsyncDownloader(view).execute(it, doc.title)
        }
      } catch (e: Exception) {
        Log.e(TAG, "DocsFragment onActivityCreated errorState", e)
        Toast.makeText(activity, "АШИПКА ЗАГРУЗКИ!", Toast.LENGTH_LONG).show()
      }
    }
    swipe_container.setOnRefreshListener(this)
    swipe_container.setColorSchemeResources(
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_blue_dark)
    swipe_container.isRefreshing = true
    swipe_container.setOnClickListener {
      swipe_container.isRefreshing = true
      viewModel.getDocs(true)
    }
    recyclerView = docs_recyclerview
    layoutManager = GridLayoutManager(activity, 1)
    recyclerView.layoutManager = layoutManager
    recyclerView.adapter = docsAdapter
  }

  override fun onRefresh() {
    viewModel.getDocs(true)
  }

  internal inner class AsyncDownloader(private val view: View) : AsyncTask<String, Long, Boolean>() {
    override fun onProgressUpdate(vararg values: Long?) {
      view.progressBar2.max = values[1]!!.toInt()
      view.progressBar2.progress = values[0]!!.toInt()
      Log.e(TAG, String.format("%d / %d", values[0], values[1]))
    }

    override fun onPostExecute(result: Boolean?) {
      if (result == null || !result) {
        Log.e(TAG, "Failed")
        Toast.makeText(activity, "Загрузка файла ${view.docCellTitle.text} оборвалась на $result%", Toast.LENGTH_SHORT).show()
        return
      }
      Log.e(TAG, "Downloaded")
      Toast.makeText(activity, "Загрузка файла ${view.docCellTitle.text} завершена полностью.", Toast.LENGTH_SHORT).show()

    }

    override fun doInBackground(vararg arg: String): Boolean {
      var fin: BufferedInputStream? = null
      val fout: FileOutputStream?
      val httpClient = OkHttpClient()
      val call = httpClient.newCall(Request.Builder().url(arg[0]).get().build())
      try {
        val response = call.execute()
        if (response.code() == 200) {
          try {
            fin = response.body()?.byteStream()!!.buffered()
            val buff = ByteArray(1024 * 4)
            var downloaded: Long = 0
            val target = response.body()?.contentLength()
            val path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS).absoluteFile
            val file = File(path, arg[1])
            file.createNewFile()
            fout = FileOutputStream(file)
            publishProgress(0L, target)
            while (true) {
              val readed = fin.read(buff, 0, 1024)
              if (readed == -1) {
                break
              }
              downloaded += readed.toLong()
              fout.write(buff, 0, readed)
              publishProgress(downloaded, target)
              if (isCancelled) {
                return false
              }
            }
            return downloaded == target
          } catch (ignore: IOException) {
            return false
          } finally {
            fin?.close()
          }
        } else {
          return false
        }
      } catch (e: IOException) {
        e.printStackTrace()
        return false
      }
    }

  }
}