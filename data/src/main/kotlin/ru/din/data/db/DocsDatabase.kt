package ru.din.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import ru.din.data.entities.getAll.DocItem

@Database(entities = [(DocItem::class)], version = 1, exportSchema = false)
abstract class DocsDatabase : RoomDatabase() {
  abstract fun getDocsDao(): DocsDao
}