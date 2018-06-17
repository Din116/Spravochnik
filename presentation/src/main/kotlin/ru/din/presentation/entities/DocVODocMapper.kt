package ru.din.presentation.entities

import ru.din.domain.common.Mapper
import ru.din.domain.entities.DocVO

class DocVODocMapper : Mapper<DocVO, Doc>() {

  override fun mapFrom(from: DocVO): Doc {
    return Doc(
        ext = from.ext,
        date = from.date,
        preview = from.preview?.let { it.photo?.sizes?.firstOrNull { "s" == it?.type }?.src },
        size = from.size,
        ownerId = from.ownerId,
        id = from.id,
        title = from.title,
        type = from.type,
        url = from.url
    )
  }
}