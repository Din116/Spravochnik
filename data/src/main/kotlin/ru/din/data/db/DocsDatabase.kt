package ru.din.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import ru.din.data.entities.Converters
import ru.din.data.entities.getAll.DocDTO

@Database(entities = [(DocDTO::class)], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DocsDatabase : RoomDatabase() {
  abstract fun getDocsDao(): DocsDao
}