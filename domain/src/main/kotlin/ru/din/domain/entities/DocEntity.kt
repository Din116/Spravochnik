package ru.din.domain.entities

data class DocEntity(
    val ext: String? = null,
    val date: Int? = null,
    val preview: Preview? = null,
    val size: Int? = null,
    val ownerId: Int? = null,
    val id: Int = 0,
    val title: String,
    val type: Int? = null,
    val url: String? = null
)