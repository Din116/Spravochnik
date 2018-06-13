package ru.din.data.mappers


import ru.din.data.entities.getAll.PhotoDTO
import ru.din.data.entities.getAll.PreviewDTO
import ru.din.data.entities.getAll.VideoDTO
import ru.din.domain.common.Mapper
import ru.din.domain.entities.PhotoVO
import ru.din.domain.entities.PreviewVO
import ru.din.domain.entities.VideoVO

class PreviewDTOToVOMapper(
    private val photoMapper: Mapper<PhotoDTO, PhotoVO>,
    private val videoMapper: Mapper<VideoDTO, VideoVO>
) : Mapper<PreviewDTO, PreviewVO>() {
  override fun mapFrom(from: PreviewDTO): PreviewVO {
    return PreviewVO(
        photo = from.photo?.let { photoMapper.mapFrom(it) },
        video = from.video?.let { videoMapper.mapFrom(it) }
    )
  }
}
