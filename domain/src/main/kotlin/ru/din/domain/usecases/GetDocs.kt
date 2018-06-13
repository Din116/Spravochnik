package ru.din.domain.usecases

import io.reactivex.Observable
import ru.din.domain.DocsRepository
import ru.din.domain.common.Transformer
import ru.din.domain.entities.DocVO

open class GetDocs(transformer: Transformer<List<DocVO>>,
                   private val docsRepository: DocsRepository) : UseCase<List<DocVO>>(transformer) {
  override fun createObservable(data: Map<String, Any>?): Observable<List<DocVO>> {
    return docsRepository.getDocs(data)
  }

}