package br.dev.s2w.alura.flix.gateway

import br.dev.s2w.alura.flix.domain.gateway.UpdateVideo
import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.gateway.repository.VideoRepository
import br.dev.s2w.alura.flix.gateway.repository.entity.VideoEntity
import br.dev.s2w.alura.flix.gateway.repository.mapper.VideoEntityMapper.toVideo
import org.springframework.stereotype.Component

@Component
class UpdateVideoImpl(
    private val videoRepository: VideoRepository
) : UpdateVideo {

    override fun modifyOne(id: Long, video: Video): Video {
        val referencedVideo = videoRepository.getReferenceById(id)
        val updatedVideo = buildUpdateVideo(referencedVideo, video)
        return videoRepository.save(updatedVideo).toVideo()
    }

    private fun buildUpdateVideo(referencedVideo: VideoEntity, video: Video): VideoEntity {
        return referencedVideo.copy(
            titulo = video.titulo,
            descricao = video.descricao,
            url = video.url
        )
    }
}