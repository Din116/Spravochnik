package ru.din.presentation.di

import okhttp3.OkHttpClient
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.din.data.api.Api
import java.util.concurrent.TimeUnit

val networkModule = applicationContext {
  bean { client() }
  bean { retrofit(get(), "https://api.vk.com/method/") }
  bean { createApi(get()) }

}

private fun createApi(retrofit: Retrofit): Api {
  return retrofit.create(Api::class.java)
}

private fun retrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
  return Retrofit.Builder()
      .client(okHttpClient)
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl(baseUrl)
      .build()
}

private fun client(): OkHttpClient {
  return OkHttpClient.Builder()
      .connectTimeout(30, TimeUnit.SECONDS)
      .readTimeout(30, TimeUnit.SECONDS)
      .build()
}

