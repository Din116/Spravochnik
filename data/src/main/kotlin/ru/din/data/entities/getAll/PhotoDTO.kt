package ru.din.data.entities.getAll

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import com.google.gson.annotations.SerializedName

@Entity
data class PhotoDTO(
    @field:SerializedName("sizes")
    val sizes: List<SizesItemDTO?>? = null
) {
  @Ignore
  constructor() : this(emptyList())
}