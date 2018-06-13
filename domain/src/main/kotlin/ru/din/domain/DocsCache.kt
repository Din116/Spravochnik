package ru.din.domain

import io.reactivex.Observable
import ru.din.domain.entities.DocVO
import ru.din.domain.entities.Optional


interface DocsCache {
  fun clear()
  fun save(doc: DocVO)
  fun remove(doc: DocVO)
  fun saveAll(docVOS: List<DocVO>)
  fun getAll(): Observable<List<DocVO>>
  fun get(docId: Int): Observable<Optional<DocVO>>
  fun search(query: String): Observable<List<DocVO>>
  fun isEmpty(): Observable<Boolean>
}