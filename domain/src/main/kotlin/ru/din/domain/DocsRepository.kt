package ru.din.domain

import io.reactivex.Observable
import ru.din.domain.entities.DocVO

interface DocsRepository {
  fun getDocs(data: Map<String, Any>?): Observable<List<DocVO>>
}