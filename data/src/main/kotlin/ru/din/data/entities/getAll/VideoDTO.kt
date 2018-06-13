package ru.din.data.entities.getAll

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import com.google.gson.annotations.SerializedName

@Entity
data class VideoDTO(

    @field:SerializedName("src")
    val src: String? = null,

    @field:SerializedName("width")
    val width: Int? = null,

    @field:SerializedName("file_size")
    val fileSize: Int? = null,

    @field:SerializedName("height")
    val height: Int? = null
) {
  @Ignore
  constructor() : this("", 0, 0, 0)
}