package ru.din.presentation.entities

import ru.din.domain.common.Mapper
import ru.din.domain.entities.DocEntity

class DocEntityDocMapper : Mapper<DocEntity, Doc>() {

  override fun mapFrom(from: DocEntity): Doc {
    return Doc(
        ext = from.ext,
        date = from.date,
        //TODO раскоментировать когда будет понятно как сохранять связанные объекты в Room

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