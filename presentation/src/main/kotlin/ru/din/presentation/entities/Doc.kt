package ru.din.presentation.entities

data class Doc(
    val ext: String? = null,
    val date: Int? = null,
    val preview: String? = null,
    val size: Int? = null,
    val ownerId: Int? = null,
    val id: Int = 0,
    val title: String,
    val type: Int? = null,
    val url: String? = null
)