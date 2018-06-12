package ru.din.presentation.docs

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.vk.sdk.VKSdk
import kotlinx.android.synthetic.main.fragment_docs.*
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject
import ru.din.presentation.LoginActivity
import ru.din.presentation.R
import ru.din.presentation.common.ImageLoader


class DocsFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
  private val TAG = "DocsFragment"

  private val imageLoader: ImageLoader by inject()
  private val viewModel by viewModel<DocsViewModel>()
  private lateinit var docsAdapter: DocsAdapter
  private lateinit var recyclerView: RecyclerView
  private lateinit var progressBar: ProgressBar
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
      swipe_container.isRefreshing = false
    })
    viewModel.errorState.observe(this, Observer { throwable ->
      throwable?.let {
        Log.e(TAG, "DocsFragment onActivityCreated errorState", throwable)
        Toast.makeText(activity, throwable.message, Toast.LENGTH_LONG).show()
      }
    })
  }

  private fun handleViewState(state: DocsViewState) {
    progressBar.visibility = if (state.showLoading) View.VISIBLE else View.GONE
    state.docs?.let { docsAdapter.addDoc(it) }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return layoutInflater.inflate(R.layout.fragment_docs, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    progressBar = docs_progress
    docsAdapter = DocsAdapter(imageLoader, { doc, view ->
      Toast.makeText(activity, "Выбран элемент с номером ${doc.id}", Toast.LENGTH_SHORT).show()
    })
    swipe_container.setOnRefreshListener(this)
    swipe_container.setColorSchemeResources(
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_blue_dark)
    swipe_container.setOnClickListener {
      swipe_container.isRefreshing = true
      viewModel.getDocs(true)
    }
    recyclerView = docs_recyclerview
    recyclerView.layoutManager = GridLayoutManager(activity, 1)
    recyclerView.adapter = docsAdapter

    logout_button.setOnClickListener {
      VKSdk.logout()
      startActivity(Intent(this@DocsFragment.context, LoginActivity::class.java))
      activity?.finish()
    }

  }

  override fun onRefresh() {
    viewModel.getDocs(true)
  }
}