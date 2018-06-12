package ru.din.presentation.entities

data class Doc(
    val ext: String? = null,
    val date: Int? = null,
    //TODO раскоментировать когда будет понятно как сохранять связанные объекты в Room

/*
    val preview: Preview? = null,
*/
    val size: Int? = null,
    val ownerId: Int? = null,
    val id: Int = 0,
    val title: String,
    val type: Int? = null,
    val url: String? = null
)