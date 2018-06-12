package ru.din.data.entities.getAll

import com.google.gson.annotations.SerializedName

data class Response(

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("items")
    var items: List<DocItem>
)
