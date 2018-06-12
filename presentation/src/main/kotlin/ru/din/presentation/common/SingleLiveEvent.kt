package ru.din.presentation.common

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread
import android.util.Log
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MutableLiveData<T>() {

  companion object {
    private const val TAG = "SingleLiveEvent"
  }

  private val mPending = AtomicBoolean(false)

  @MainThread
  override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
    if (hasActiveObservers()) {
      Log.w(TAG, "Зарегистрировано несколько наблюдателей, но только один будет уведомлен об изменениях.")
    }
    super.observe(owner, Observer<T> { t ->
      if (mPending.compareAndSet(true, false)) {
        observer.onChanged(t)
      }
    })
  }

  @MainThread
  override fun setValue(t: T?) {
    mPending.set(true)
    super.setValue(t)
  }

  /**
   * Используется для случаев, когда T пуст, чтобы сделать вызовы более чистыми.
   */
  @MainThread
  fun call() {
    this.value = null
  }
}