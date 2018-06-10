package ru.din.presentation

import ru.din.domain.common.Mapper
import ru.din.domain.entities.DocEntity
import ru.din.presentation.entities.Doc

class DocEntityDocMapper : Mapper<DocEntity, Doc>() {

  override fun mapFrom(from: DocEntity): Doc {
    return Doc(
        ext = from.ext,
        date = from.date,
        preview = from.preview,
        size = from.size,
        ownerId = from.ownerId,
        id = from.id,
        title = from.title,
        type = from.type,
        url = from.url
    )
  }
}