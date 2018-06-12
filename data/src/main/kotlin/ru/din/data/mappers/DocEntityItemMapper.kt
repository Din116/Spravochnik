package ru.din.data.mappers

import ru.din.data.entities.getAll.DocItem
import ru.din.domain.common.Mapper
import ru.din.domain.entities.DocEntity

class DocEntityItemMapper : Mapper<DocEntity, DocItem>() {

  override fun mapFrom(from: DocEntity): DocItem {
    return DocItem(
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
