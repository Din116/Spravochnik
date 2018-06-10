package ru.din.data.entities.getAll

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName
import ru.din.domain.entities.Photo
import ru.din.domain.entities.Video
@Entity
data class Preview(

    @field:SerializedName("photo")
    val photo: Photo? = null,

    @field:SerializedName("video")
    val video: Video? = null
)