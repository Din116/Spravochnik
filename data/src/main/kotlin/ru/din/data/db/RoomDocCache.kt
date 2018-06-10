package ru.din.data.db

import io.reactivex.Observable
import ru.din.data.entities.getAll.DocItem
import ru.din.domain.DocsCache
import ru.din.domain.common.Mapper
import ru.din.domain.entities.DocEntity
import ru.din.domain.entities.Optional

class RoomDocCache(database: DocsDatabase,
                   private val entityToItemMapper: Mapper<DocEntity, DocItem>,
                   private val itemToEntityMapper: Mapper<DocItem, DocEntity>) : DocsCache {
  private val dao: DocsDao = database.getDocsDao()

  override fun clear() {
    dao.clear()
  }

  override fun save(docEntity: DocEntity) {
    dao.saveDoc(entityToItemMapper.mapFrom(docEntity))
  }

  override fun remove(docEntity: DocEntity) {
    dao.removeDoc(entityToItemMapper.mapFrom(docEntity))
  }

  override fun saveAll(docEntities: List<DocEntity>) {
    dao.saveAllDocs(docEntities.map { entityToItemMapper.mapFrom(it) })
  }

  override fun getAll(): Observable<List<DocEntity>> {
    return Observable.fromCallable { dao.getDocs().map { itemToEntityMapper.mapFrom(it) } }
  }

  override fun get(movieId: Int): Observable<Optional<DocEntity>> {

    return Observable.fromCallable {
      val movieData = dao.get(movieId)
      movieData?.let {
        Optional.of(itemToEntityMapper.mapFrom(it))
      } ?: Optional.empty()
    }
  }

  override fun isEmpty(): Observable<Boolean> {
    return Observable.fromCallable { dao.getDocs().isEmpty() }
  }

  override fun search(query: String): Observable<List<DocEntity>> {
    val searchQuery = "%$query%"
    return Observable.fromCallable {
      dao.search(searchQuery).map { itemToEntityMapper.mapFrom(it) }
    }
  }
}