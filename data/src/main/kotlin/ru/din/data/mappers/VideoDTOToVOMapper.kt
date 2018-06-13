package ru.din.data.mappers

import ru.din.data.entities.getAll.VideoDTO
import ru.din.domain.entities.VideoVO
import ru.din.domain.common.Mapper

class VideoDTOToVOMapper : Mapper<VideoDTO, VideoVO>() {
  override fun mapFrom(from: VideoDTO): VideoVO {
    return VideoVO(
        src = from.src,
        width = from.width,
        fileSize = from.fileSize,
        height = from.height
    )
  }
}
