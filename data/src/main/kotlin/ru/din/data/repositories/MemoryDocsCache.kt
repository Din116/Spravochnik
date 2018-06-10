package ru.din.data.repositories

import io.reactivex.Observable
import ru.din.domain.DocsCache
import ru.din.domain.entities.DocEntity
import ru.din.domain.entities.Optional

class MemoryDocsCache : DocsCache {

    private val docs: LinkedHashMap<Int, DocEntity> = LinkedHashMap()

    override fun isEmpty(): Observable<Boolean> {
        return Observable.fromCallable { docs.isEmpty() }
    }

    override fun remove(docEntity: DocEntity) {
        docs.remove(docEntity.id)
    }

    override fun clear() {
        docs.clear()
    }

    override fun save(docEntity: DocEntity) {
        docs[docEntity.id] = docEntity
    }

    override fun saveAll(docEntitie: List<DocEntity>) {
        docEntitie.forEach { docEntity -> this.docs[docEntity.id] = docEntity }
    }

    override fun getAll(): Observable<List<DocEntity>> {
        return Observable.just(docs.values.toList())
    }

    override fun get(docId: Int): Observable<Optional<DocEntity>> {
        return Observable.just(Optional.of(docs[docId]))
    }

    override fun search(query: String): Observable<List<DocEntity>> {
        return Observable.fromCallable {
            docs.values.filter {
                it.title.contains(query)
            }
        }
    }
}