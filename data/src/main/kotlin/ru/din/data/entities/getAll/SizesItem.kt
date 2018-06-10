package ru.din.data.entities.getAll

import com.google.gson.annotations.SerializedName

data class SizesItem(

    @field:SerializedName("src")
    val src: String? = null,

    @field:SerializedName("width")
    val width: Int? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("height")
    val height: Int? = null
)