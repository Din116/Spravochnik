package ru.din.domain.usecases

import io.reactivex.Observable
import ru.din.domain.common.Transformer

abstract class UseCase<T>(private val transformer: Transformer<T>) {

  abstract fun createObservable(data: Map<String, Any>? = null): Observable<T>

  fun observable(withData: Map<String, Any>? = null): Observable<T> {
    return createObservable(withData).compose(transformer)
  }

}