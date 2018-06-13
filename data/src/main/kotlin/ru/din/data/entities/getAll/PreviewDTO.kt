package ru.din.data.entities.getAll

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import com.google.gson.annotations.SerializedName

@Entity
data class PreviewDTO(
    @Embedded
    @field:SerializedName("photo")
    val photo: PhotoDTO? = null,

    @Embedded
    @field:SerializedName("video")
    val video: VideoDTO? = null
) {
  @Ignore
  constructor() : this(PhotoDTO(), VideoDTO())
}