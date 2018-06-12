package ru.din.presentation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
  private val TAG = "LoginActivity"

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)
    if (VKSdk.isLoggedIn()) {
      startActivity(Intent(this, MainActivity::class.java))
      finish()
    }
    login_button.setOnClickListener {
      if (VKSdk.isLoggedIn()) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
      } else {
        VKSdk.login(this, "docs")
      }
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    val vkCallback: VKCallback<VKAccessToken> = object : VKCallback<VKAccessToken> {
      override fun onResult(res: VKAccessToken) {
        login_button.text = "Начать"
      }

      override fun onError(error: VKError) {
        Log.e(TAG, "Авторизация не пройдена, данные ошибки: ${error.errorCode}/${error.errorMessage}/${error.errorReason}")
      }
    }

    if (!VKSdk.onActivityResult(requestCode, resultCode, data, vkCallback)) {
      super.onActivityResult(requestCode, resultCode, data)
    }
  }
}
