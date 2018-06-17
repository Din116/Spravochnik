package ru.din.presentation.di

import android.arch.persistence.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext
import ru.din.data.db.DocsDatabase
import ru.din.data.db.RoomDocCache
import ru.din.data.entities.getAll.*
import ru.din.data.mappers.*
import ru.din.data.repositories.CachedDocsDataStore
import ru.din.data.repositories.DocsRepositoryImpl
import ru.din.data.repositories.MemoryDocsCache
import ru.din.data.repositories.RemoteDocsDataStore
import ru.din.domain.DocsCache
import ru.din.domain.DocsDataStore
import ru.din.domain.DocsRepository
import ru.din.domain.common.Mapper
import ru.din.domain.entities.*
import ru.din.presentation.entities.Doc
import ru.din.presentation.entities.DocVODocMapper

val dataModule = applicationContext {
  bean { Room.databaseBuilder(androidApplication(), DocsDatabase::class.java, "docs_db").fallbackToDestructiveMigration().build() }
  bean("SizeitemVOToDTOMapper") { SizeitemVOToDTOMapper() as Mapper<SizesItemVO, SizesItemDTO> }
  bean("SizeitemDTOToVOMapper") { SizeitemDTOToVOMapper() as Mapper<SizesItemDTO, SizesItemVO> }
  bean("VideoVOToDTOMapper") { VideoVOToDTOMapper() as Mapper<VideoVO, VideoDTO> }
  bean("VideoDTOToVOMapper") { VideoDTOToVOMapper() as Mapper<VideoDTO, VideoVO> }
  bean("PhotoVOToDTOMapper") { PhotoVOToDTOMapper(get("SizeitemVOToDTOMapper")) as Mapper<PhotoVO, PhotoDTO> }
  bean("PhotoDTOToVOMapper") { PhotoDTOToVOMapper(get("SizeitemDTOToVOMapper")) as Mapper<PhotoDTO, PhotoVO> }
  bean("PreviewVOToDTOMapper") { PreviewVOToDTOMapper(get("PhotoVOToDTOMapper"), get("VideoVOToDTOMapper")) as Mapper<PreviewVO, PreviewDTO> }
  bean("PreviewDTOToVOMapper") { PreviewDTOToVOMapper(get("PhotoDTOToVOMapper"), get("VideoDTOToVOMapper")) as Mapper<PreviewDTO, PreviewVO> }
  bean("DocVOToDTOMapper") { DocVOToDTOMapper(get("PreviewVOToDTOMapper")) as Mapper<DocVO, DocDTO> }
  bean("DocDTOToVOMapper") { DocDTOToVOMapper(get("PreviewDTOToVOMapper")) as Mapper<DocDTO, DocVO> }
  bean("DocVODocMapper") { DocVODocMapper() as Mapper<DocVO, Doc> }
  bean { DocsRepositoryImpl(get(), get(), get("DocDTOToVOMapper")) as DocsRepository }
  bean { MemoryDocsCache() as DocsCache }
  bean { RoomDocCache(get(), get("DocVOToDTOMapper"), get("DocDTOToVOMapper")) as DocsCache }
  bean { RemoteDocsDataStore(get(), get("DocVOToDTOMapper")) as DocsDataStore }
  bean { CachedDocsDataStore(get()) as DocsDataStore }
}