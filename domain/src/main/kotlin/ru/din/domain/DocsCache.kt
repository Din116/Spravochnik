package ru.din.domain

import io.reactivex.Observable
import ru.din.domain.entities.DocEntity
import ru.din.domain.entities.Optional


interface DocsCache {
  fun clear()
  fun save(docEntity: DocEntity)
  fun remove(docEntity: DocEntity)
  fun saveAll(docEntitie: List<DocEntity>)
  fun getAll(): Observable<List<DocEntity>>
  fun get(movieId: Int): Observable<Optional<DocEntity>>
  fun search(query: String): Observable<List<DocEntity>>
  fun isEmpty(): Observable<Boolean>
}