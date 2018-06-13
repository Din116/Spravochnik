package ru.din.data.entities.getAll

import com.google.gson.annotations.SerializedName

class ResultDTO {
  @field:SerializedName("response")
  lateinit var response: ResponseDTO
}