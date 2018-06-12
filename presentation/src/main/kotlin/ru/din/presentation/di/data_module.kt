package ru.din.presentation.di

import android.arch.persistence.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext
import ru.din.data.db.DocsDatabase
import ru.din.data.db.RoomDocCache
import ru.din.data.entities.getAll.DocItem
import ru.din.data.mappers.DocEntityItemMapper
import ru.din.data.mappers.DocItemEntityMapper
import ru.din.data.repositories.CachedDocsDataStore
import ru.din.data.repositories.DocsRepositoryImpl
import ru.din.data.repositories.MemoryDocsCache
import ru.din.data.repositories.RemoteDocsDataStore
import ru.din.domain.DocsCache
import ru.din.domain.DocsDataStore
import ru.din.domain.DocsRepository
import ru.din.domain.common.Mapper
import ru.din.domain.entities.DocEntity
import ru.din.presentation.entities.Doc
import ru.din.presentation.entities.DocEntityDocMapper

val dataModule = applicationContext {
  bean { Room.databaseBuilder(androidApplication(), DocsDatabase::class.java, "docs_db").build() }
  bean("DocEntityItemMapper") { DocEntityItemMapper() as Mapper<DocEntity, DocItem> }
  bean("DocItemEntityMapper") { DocItemEntityMapper() as Mapper<DocItem, DocEntity> }
  bean("DocEntityDocMapper") { DocEntityDocMapper() as Mapper<DocEntity, Doc> }
  bean { DocsRepositoryImpl(get(), get(), get("DocItemEntityMapper")) as DocsRepository }
  bean { MemoryDocsCache() as DocsCache }
  bean { RoomDocCache(get(), get("DocEntityItemMapper"), get("DocItemEntityMapper")) as DocsCache }
  bean { RemoteDocsDataStore(get(), get("DocEntityItemMapper")) as DocsDataStore }
  bean { CachedDocsDataStore(get()) as DocsDataStore }
}