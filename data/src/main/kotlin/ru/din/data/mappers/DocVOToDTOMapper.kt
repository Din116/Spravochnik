package ru.din.data.mappers

import ru.din.data.entities.getAll.DocDTO
import ru.din.data.entities.getAll.PreviewDTO
import ru.din.domain.common.Mapper
import ru.din.domain.entities.DocVO
import ru.din.domain.entities.PreviewVO

class DocVOToDTOMapper(private val previewMapper: Mapper<PreviewVO, PreviewDTO>) : Mapper<DocVO, DocDTO>() {

  override fun mapFrom(from: DocVO): DocDTO {
    return DocDTO(
        ext = from.ext,
        date = from.date,
        preview = from.preview?.let { previewMapper.mapFrom(it) },
        size = from.size,
        ownerId = from.ownerId,
        id = from.id,
        title = from.title,
        type = from.type,
        url = from.url
    )
  }
}
