package ru.din.presentation.docs

import android.arch.lifecycle.MutableLiveData
import ru.din.domain.common.Mapper
import ru.din.domain.entities.DocVO
import ru.din.domain.usecases.GetDocs
import ru.din.presentation.common.BaseViewModel
import ru.din.presentation.common.SingleLiveEvent
import ru.din.presentation.entities.Doc

class DocsViewModel(private val getDocs: GetDocs,
                    private val docsMapper: Mapper<DocVO, Doc>) : BaseViewModel() {
  var viewState: MutableLiveData<DocsViewState> = MutableLiveData()
  var errorState: SingleLiveEvent<Throwable?> = SingleLiveEvent()

  init {
    viewState.value = DocsViewState()
  }

  fun getDocs(noCache: Boolean) {
    addDisposable(getDocs.observable(mapOf(Pair("nocache", noCache)))
        .flatMap { docsMapper.observable(it) }
        .subscribe({ docs ->
          viewState.value?.let {
            val newState = this.viewState.value?.copy(showLoading = false, docs = docs)
            this.viewState.value = newState
            this.errorState.value = null
          }
        }, {
          viewState.value = viewState.value?.copy(showLoading = false)
          errorState.value = it
        }))
  }
}