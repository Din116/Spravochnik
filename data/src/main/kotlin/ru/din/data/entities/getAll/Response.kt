package ru.din.data.entities.getAll

import com.google.gson.annotations.SerializedName

data class Response(

    @field:SerializedName("response")
    val response: Response? = null,

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("items")
    val items: List<DocItem>
)