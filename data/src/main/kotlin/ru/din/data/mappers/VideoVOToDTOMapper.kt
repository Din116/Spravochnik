package ru.din.data.mappers

import ru.din.data.entities.getAll.VideoDTO
import ru.din.domain.common.Mapper
import ru.din.domain.entities.VideoVO

class VideoVOToDTOMapper : Mapper<VideoVO, VideoDTO>() {
  override fun mapFrom(from: VideoVO): VideoDTO {
    return VideoDTO(
        src = from.src,
        width = from.width,
        fileSize = from.fileSize,
        height = from.height
    )
  }
}
