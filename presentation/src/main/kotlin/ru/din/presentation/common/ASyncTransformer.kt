package ru.din.presentation.common

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers
import ru.din.domain.common.Transformer

class ASyncTransformer<T> : Transformer<T>() {
  override fun apply(upstream: Observable<T>): ObservableSource<T> {
    return upstream.subscribeOn(Schedulers.io()).observeOn(mainThread())
  }
}