package ru.din.data.mappers

import ru.din.data.entities.getAll.SizesItemDTO
import ru.din.domain.common.Mapper
import ru.din.domain.entities.SizesItemVO

class SizeitemVOToDTOMapper : Mapper<SizesItemVO, SizesItemDTO>() {
  override fun mapFrom(from: SizesItemVO): SizesItemDTO {
    return SizesItemDTO(
        src = from.src,
        width = from.width,
        type = from.type,
        height = from.height
    )
  }
}
