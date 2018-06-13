package ru.din.data.mappers


import ru.din.data.entities.getAll.PhotoDTO
import ru.din.data.entities.getAll.PreviewDTO
import ru.din.data.entities.getAll.VideoDTO
import ru.din.domain.common.Mapper
import ru.din.domain.entities.PhotoVO
import ru.din.domain.entities.PreviewVO
import ru.din.domain.entities.VideoVO


class PreviewVOToDTOMapper(
    private val photoMapper: Mapper<PhotoVO, PhotoDTO>,
    private val videoMapper: Mapper<VideoVO, VideoDTO>
) : Mapper<PreviewVO, PreviewDTO>() {

  override fun mapFrom(from: PreviewVO): PreviewDTO {
    return PreviewDTO(
        photo = from.photo?.let { photoMapper.mapFrom(it) },
        video = from.video?.let { videoMapper.mapFrom(it) }
    )
  }
}
