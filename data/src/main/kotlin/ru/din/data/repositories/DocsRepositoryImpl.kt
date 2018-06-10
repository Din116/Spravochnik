package ru.din.data.repositories

import io.reactivex.Observable
import ru.din.data.api.Api
import ru.din.data.entities.getAll.DocItem
import ru.din.domain.DocsCache
import ru.din.domain.DocsDataStore
import ru.din.domain.DocsRepository
import ru.din.domain.common.Mapper
import ru.din.domain.entities.DocEntity

class DocsRepositoryImpl(api: Api,
                         private val cache: DocsCache,
                         docDataMapper: Mapper<DocItem, DocEntity>) : DocsRepository {

  private val memoryDataStore: DocsDataStore
  private val remoteDataStore: DocsDataStore

  init {
    memoryDataStore = CachedDocsDataStore(cache)
    remoteDataStore = RemoteDocsDataStore(api, docDataMapper)
  }

  override fun getDocs(): Observable<List<DocEntity>> {
    return cache.isEmpty().flatMap { empty ->
      if (!empty) {
        return@flatMap memoryDataStore.getDocs()
      } else {
        return@flatMap remoteDataStore.getDocs().doOnNext { cache.saveAll(it) }
      }
    }
  }


}