package ru.din.presentation.docs

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.vk.sdk.VKSdk
import kotlinx.android.synthetic.main.fragment_docs.*
import ru.din.presentation.LoginActivity
import ru.din.presentation.R

class DocsFragment : Fragment() {
  private val TAG = "DocsFragment"
  private lateinit var recyclerView: RecyclerView
  private lateinit var progressBar: ProgressBar
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d(TAG, "DocsFragment 1")
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    Log.d(TAG, "DocsFragment 2")
    return layoutInflater.inflate(R.layout.fragment_docs, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    logout_button.setOnClickListener {
      VKSdk.logout()
      startActivity(Intent(this@DocsFragment.context, LoginActivity::class.java))
    }
    progressBar = docs_progress

    recyclerView = docs_recyclerview
    recyclerView.layoutManager = GridLayoutManager(activity, 2)
  }

}