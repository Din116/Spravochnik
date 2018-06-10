package ru.din.domain

import io.reactivex.Observable
import ru.din.domain.entities.DocEntity

interface DocsDataStore {
  fun getDocs(): Observable<List<DocEntity>>
}