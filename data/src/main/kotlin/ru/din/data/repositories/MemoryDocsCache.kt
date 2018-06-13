package ru.din.data.repositories

import io.reactivex.Observable
import ru.din.domain.DocsCache
import ru.din.domain.entities.DocVO
import ru.din.domain.entities.Optional

class MemoryDocsCache : DocsCache {

    private val docs: LinkedHashMap<Int, DocVO> = LinkedHashMap()

    override fun isEmpty(): Observable<Boolean> {
        return Observable.fromCallable { docs.isEmpty() }
    }

    override fun remove(doc: DocVO) {
        docs.remove(doc.id)
    }

    override fun clear() {
        docs.clear()
    }

    override fun save(doc: DocVO) {
        docs[doc.id] = doc
    }

    override fun saveAll(docs: List<DocVO>) {
        docs.forEach { doc -> this.docs[doc.id] = doc }
    }

    override fun getAll(): Observable<List<DocVO>> {
        return Observable.just(docs.values.toList())
    }

    override fun get(docId: Int): Observable<Optional<DocVO>> {
        return Observable.just(Optional.of(docs[docId]))
    }

    override fun search(query: String): Observable<List<DocVO>> {
        return Observable.fromCallable {
            docs.values.filter {
                it.title.contains(query)
            }
        }
    }
}