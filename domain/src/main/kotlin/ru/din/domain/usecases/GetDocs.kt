package ru.din.domain.usecases

import io.reactivex.Observable
import ru.din.domain.DocsRepository
import ru.din.domain.common.Transformer
import ru.din.domain.entities.DocEntity

open class GetDocs(transformer: Transformer<List<DocEntity>>,
                   private val docsRepository: DocsRepository) : UseCase<List<DocEntity>>(transformer) {
  override fun createObservable(data: Map<String, Any>?): Observable<List<DocEntity>> {
    return docsRepository.getDocs()
  }

}