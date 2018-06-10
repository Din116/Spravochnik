package ru.din.data.repositories

import io.reactivex.Observable
import ru.din.domain.DocsCache
import ru.din.domain.DocsDataStore
import ru.din.domain.entities.DocEntity

class CachedDocsDataStore(private val docsCache: DocsCache) : DocsDataStore {

  override fun getDocs(): Observable<List<DocEntity>> {
    return docsCache.getAll()
  }

}