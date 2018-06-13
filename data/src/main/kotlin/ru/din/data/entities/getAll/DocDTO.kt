package ru.din.data.entities.getAll

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "docs")
data class DocDTO(

    @field:SerializedName("ext")
    val ext: String? = null,

    @field:SerializedName("date")
    val date: Int? = null,

    @Embedded
    @field:SerializedName("preview")
    val preview: PreviewDTO? = null,

    @field:SerializedName("size")
    val size: Int? = null,


    @field:SerializedName("owner_id")
    val ownerId: Int? = null,

    @field:SerializedName("id")
    @PrimaryKey
    val id: Int = -1,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("type")
    val type: Int? = null,

    @field:SerializedName("url")
    val url: String? = null
) {
    @Ignore
    constructor() : this("", 0, PreviewDTO(), 0, 0, -1, "", 0, "")
}