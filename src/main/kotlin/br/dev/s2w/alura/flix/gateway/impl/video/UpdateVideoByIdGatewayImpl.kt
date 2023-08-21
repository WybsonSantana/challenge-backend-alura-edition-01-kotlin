package br.dev.s2w.alura.flix.gateway.impl.video

import br.dev.s2w.alura.flix.domain.exception.VideoNotFoundException
import br.dev.s2w.alura.flix.domain.gateway.video.UpdateVideoByIdGateway
import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.gateway.repository.VideoRepository
import br.dev.s2w.alura.flix.gateway.repository.entity.VideoEntity
import br.dev.s2w.alura.flix.gateway.repository.mapper.CategoriaEntityMapper.toCategoriaEntity
import br.dev.s2w.alura.flix.gateway.repository.mapper.VideoEntityMapper.toVideo
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.VIDEO_NOT_FOUND_EXCEPTION_MESSAGE
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.stereotype.Component

@Component
class UpdateVideoByIdGatewayImpl(
    private val videoRepository: VideoRepository
) : UpdateVideoByIdGateway {

    override fun modifyOneBy(id: Long, video: Video): Video {
        val referencedVideo = try {
            videoRepository.getReferenceById(id)
        } catch (e: JpaObjectRetrievalFailureException) {
            throw VideoNotFoundException(VIDEO_NOT_FOUND_EXCEPTION_MESSAGE)
        }
        val updatedVideo = buildUpdateVideo(referencedVideo, video)
        return videoRepository.save(updatedVideo).toVideo()
    }

    private fun buildUpdateVideo(referencedVideo: VideoEntity, video: Video): VideoEntity {
        return referencedVideo.copy(
            titulo = video.titulo,
            descricao = video.descricao,
            url = video.url,
            categoria = video.categoria?.toCategoriaEntity()
        )
    }
}