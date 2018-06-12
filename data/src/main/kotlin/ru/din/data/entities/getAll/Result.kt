package ru.din.data.entities.getAll

import com.google.gson.annotations.SerializedName

class Result {
  @field:SerializedName("response")
  lateinit var response: Response
}