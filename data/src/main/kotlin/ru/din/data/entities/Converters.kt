package ru.din.data.entities

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.din.data.entities.getAll.SizesItemDTO
import java.util.*

class Converters {
  @TypeConverter
  fun fromTimestamp(value: Long?): Date? {
    return if (value == null) Date() else Date(value)
  }

  @TypeConverter
  fun dateToTimestamp(date: Date?): Long? {
    return date?.time ?: 0
  }

  @TypeConverter
  fun fromStringToArray(value: String?): Array<String>? {
    return value?.split(",")?.toTypedArray() ?: arrayOf()
  }

  @TypeConverter
  fun stringToStringArray(strings: Array<String>?): String? {
    return strings?.joinToString(",") ?: ""
  }

  @TypeConverter
  fun stringToSizeItems(json: String): List<SizesItemDTO> {
    val gson = Gson()
    val type = object : TypeToken<List<SizesItemDTO>>() {
    }.type
    return gson.fromJson(json, type)
  }

  @TypeConverter
  fun sizeItemsToString(list: List<SizesItemDTO>): String {
    val gson = Gson()
    val type = object : TypeToken<List<SizesItemDTO>>() {
    }.type
    return gson.toJson(list, type)
  }
}