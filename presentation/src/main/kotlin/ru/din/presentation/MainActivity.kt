package ru.din.presentation

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import kotlinx.android.synthetic.main.activity_main.*
import ru.din.presentation.docs.DocsFragment
import ru.din.presentation.info.InfoFragment

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
  private val TAG = "MainActivity"
  private lateinit var navigationBar: BottomNavigationView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
          .replace(R.id.container, DocsFragment(), "docs")
          .commitNow()
      title = getString(R.string.app_name)
    }
    navigationBar = botNavView
    navigationBar.setOnNavigationItemSelectedListener(this)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    val vkCallback: VKCallback<VKAccessToken> = object : VKCallback<VKAccessToken> {
      override fun onResult(res: VKAccessToken) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, DocsFragment(), "docs")
            .commitNow()
        title = getString(R.string.app_name)
      }

      override fun onError(error: VKError) {
        val s = "Авторизация не пройдена, данные ошибки: ${error.errorCode}/${error.errorMessage}/${error.errorReason}"
        Log.e(TAG, s)
        Toast.makeText(this@MainActivity, s, Toast.LENGTH_LONG).show()
      }
    }

    if (!VKSdk.onActivityResult(requestCode, resultCode, data, vkCallback)) {
      super.onActivityResult(requestCode, resultCode, data)
    }
  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    Log.e(TAG, "onNavigationItemSelected 1")
    if (item.itemId == navigationBar.selectedItemId) {
      Log.e(TAG, "onNavigationItemSelected 3")
      return false
    }
    Log.e(TAG, "onNavigationItemSelected 2")
    when (item.itemId) {
      R.id.action_docs -> {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, DocsFragment(), "docs")
            .commitNow()
        title = getString(R.string.docs_screen)
      }

      R.id.action_info -> {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, InfoFragment(), "info")
            .commitNow()
        title = getString(R.string.info_screen)
      }

      R.id.action_logout -> {
        VKSdk.logout()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
      }
    }

    return true
  }
}