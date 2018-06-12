package ru.din.presentation.docs

import ru.din.presentation.entities.Doc

data class DocsViewState(
    var showLoading: Boolean = true,
    var docs: List<Doc>? = null
)