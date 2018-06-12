package ru.din.data.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ru.din.data.entities.getAll.Result

interface Api {

  @GET("docs.get")
  fun getDocs(@Query("owner_id") owner_id: String, @Query("v") version: String, @Query("access_token") token: String): Observable<Result>

}