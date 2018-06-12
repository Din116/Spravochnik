package ru.din.presentation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import ru.din.presentation.docs.DocsFragment

class MainActivity : AppCompatActivity() {
  private val TAG = "MainActivity"

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
          .replace(R.id.container, DocsFragment(), "docs")
          .commitNow()
      title = getString(R.string.app_name)
    }
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
}