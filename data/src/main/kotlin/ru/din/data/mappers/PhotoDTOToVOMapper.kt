package ru.din.data.mappers

import ru.din.data.entities.getAll.PhotoDTO
import ru.din.data.entities.getAll.SizesItemDTO
import ru.din.domain.common.Mapper
import ru.din.domain.entities.PhotoVO
import ru.din.domain.entities.SizesItemVO

class PhotoDTOToVOMapper(private val sizeitemMapper: Mapper<SizesItemDTO, SizesItemVO>) : Mapper<PhotoDTO, PhotoVO>() {
  override fun mapFrom(from: PhotoDTO): PhotoVO {
    return PhotoVO(
        sizes = from.sizes?.map { it?.let { sizeitemMapper.mapFrom(it) } }
    )
  }
}
