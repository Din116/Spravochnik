package ru.din.domain

import io.reactivex.Observable
import ru.din.domain.entities.DocVO

interface DocsDataStore {
  fun getDocs(): Observable<List<DocVO>>
}