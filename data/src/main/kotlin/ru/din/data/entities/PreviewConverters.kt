package ru.din.data.entities

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.din.data.entities.getAll.Preview


class PreviewConverters {
  @TypeConverter
  fun fromPreview(preview: Preview): String {
    val type = object : TypeToken<Preview>() {}.type
    return Gson().toJson(preview, type)
  }

  @TypeConverter
  fun toPreview(value: String): Preview {
    val type = object : TypeToken<Preview>() {}.type
    return Gson().fromJson(value, type)
  }
}