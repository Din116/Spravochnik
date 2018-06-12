package ru.din.data.entities.getAll

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Photo(

    @field:SerializedName("sizes")
    val sizes: List<SizesItem?>? = null
)