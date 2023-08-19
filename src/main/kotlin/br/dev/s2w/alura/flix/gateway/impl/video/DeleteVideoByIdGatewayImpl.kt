package br.dev.s2w.alura.flix.gateway.impl.video

import br.dev.s2w.alura.flix.domain.exception.VideoNotFoundException
import br.dev.s2w.alura.flix.domain.gateway.video.DeleteVideoByIdGateway
import br.dev.s2w.alura.flix.gateway.repository.VideoRepository
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.VIDEO_NOT_FOUND_EXCEPTION_MESSAGE
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Component

@Component
class DeleteVideoByIdGatewayImpl(
    private val videoRepository: VideoRepository
) : DeleteVideoByIdGateway {

    override fun removeOneBy(id: Long) {
        try {
            videoRepository.deleteById(id)
        } catch (e: EmptyResultDataAccessException) {
            throw VideoNotFoundException(VIDEO_NOT_FOUND_EXCEPTION_MESSAGE)
        }
    }
}