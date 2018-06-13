package ru.din.data.db

import io.reactivex.Observable
import ru.din.data.entities.getAll.DocDTO
import ru.din.domain.DocsCache
import ru.din.domain.common.Mapper
import ru.din.domain.entities.DocVO
import ru.din.domain.entities.Optional

class RoomDocCache(database: DocsDatabase,
                   private val docVOToDTOMapper: Mapper<DocVO, DocDTO>,
                   private val docDTOToVOMapper: Mapper<DocDTO, DocVO>) : DocsCache {
  private val docsDao: DocsDao = database.getDocsDao()

  override fun clear() {
    docsDao.clear()
  }

  override fun save(doc: DocVO) {
    docsDao.saveDoc(docVOToDTOMapper.mapFrom(doc))
  }

  override fun remove(doc: DocVO) {
    docsDao.removeDoc(docVOToDTOMapper.mapFrom(doc))
  }

  override fun saveAll(docs: List<DocVO>) {
    docsDao.saveAllDocs(docs.map { docVOToDTOMapper.mapFrom(it) })
  }

  override fun getAll(): Observable<List<DocVO>> {
    return Observable.fromCallable { docsDao.getDocs().map { docDTOToVOMapper.mapFrom(it) } }
  }

  override fun get(docId: Int): Observable<Optional<DocVO>> {

    return Observable.fromCallable {
      val docData = docsDao.get(docId)
      docData?.let {
        Optional.of(docDTOToVOMapper.mapFrom(it))
      } ?: Optional.empty()
    }
  }

  override fun isEmpty(): Observable<Boolean> {
    return Observable.fromCallable { docsDao.getDocs().isEmpty() }
  }

  override fun search(query: String): Observable<List<DocVO>> {
    val searchQuery = "%$query%"
    return Observable.fromCallable {
      docsDao.search(searchQuery).map { docDTOToVOMapper.mapFrom(it) }
    }
  }
}