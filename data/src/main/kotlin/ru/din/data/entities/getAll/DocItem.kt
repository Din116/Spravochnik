package ru.din.data.entities.getAll

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.google.gson.annotations.SerializedName
import ru.din.data.entities.PreviewConverters
import ru.din.domain.entities.Preview

@Entity(tableName = "docs")
@TypeConverters(PreviewConverters::class)
data class DocItem(

    @field:SerializedName("ext")
    val ext: String? = null,

    @field:SerializedName("date")
    val date: Int? = null,

/*    @field:SerializedName("preview")
    val preview: Preview? = null,*/

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
)