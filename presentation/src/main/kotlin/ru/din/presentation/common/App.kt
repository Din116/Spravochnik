package ru.din.presentation.common

import android.app.Application
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKAccessTokenTracker
import com.vk.sdk.VKSdk
import org.koin.android.ext.android.startKoin
import ru.din.presentation.LoginActivity

class App : Application() {
  var vkAccessTokenTracker: VKAccessTokenTracker = object : VKAccessTokenTracker() {
    override fun onVKAccessTokenChanged(oldToken: VKAccessToken?, newToken: VKAccessToken?) {
      if (newToken == null) {
        startActivity(Intent(applicationContext, LoginActivity::class.java).addFlags(FLAG_ACTIVITY_NEW_TASK))

      }
    }
  }

  override fun onCreate() {
    super.onCreate()
    vkAccessTokenTracker.startTracking()
    VKSdk.initialize(this)
    startKoin(this, listOf())
  }

}