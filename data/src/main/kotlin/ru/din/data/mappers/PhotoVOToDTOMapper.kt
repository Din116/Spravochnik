package ru.din.data.mappers

import ru.din.data.entities.getAll.PhotoDTO
import ru.din.data.entities.getAll.SizesItemDTO
import ru.din.domain.common.Mapper
import ru.din.domain.entities.PhotoVO
import ru.din.domain.entities.SizesItemVO

class PhotoVOToDTOMapper(private val sizeitemMapper: Mapper<SizesItemVO, SizesItemDTO>) : Mapper<PhotoVO, PhotoDTO>() {
  override fun mapFrom(from: PhotoVO): PhotoDTO {
    return PhotoDTO(
        sizes = from.sizes?.map { it?.let { sizeitemMapper.mapFrom(it) } }
    )
  }
}
