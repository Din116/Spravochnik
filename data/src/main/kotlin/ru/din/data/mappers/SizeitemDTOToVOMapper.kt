package ru.din.data.mappers

import ru.din.data.entities.getAll.SizesItemDTO
import ru.din.domain.common.Mapper
import ru.din.domain.entities.SizesItemVO

class SizeitemDTOToVOMapper : Mapper<SizesItemDTO, SizesItemVO>() {
  override fun mapFrom(from: SizesItemDTO): SizesItemVO {
    return SizesItemVO(
        src = from.src,
        width = from.width,
        type = from.type,
        height = from.height
    )
  }
}
