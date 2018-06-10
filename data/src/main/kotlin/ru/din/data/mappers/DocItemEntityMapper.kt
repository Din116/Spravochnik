package ru.din.data.mappers

import ru.din.data.entities.getAll.DocItem
import ru.din.domain.common.Mapper
import ru.din.domain.entities.DocEntity

class DocItemEntityMapper : Mapper<DocItem, DocEntity>() {

  override fun mapFrom(from: DocItem): DocEntity {
    return DocEntity(
        ext = from.ext,
        date = from.date,
/*
        preview = from.preview,
*/
        size = from.size,
        ownerId = from.ownerId,
        id = from.id,
        title = from.title,
        type = from.type,
        url = from.url
    )
  }
}