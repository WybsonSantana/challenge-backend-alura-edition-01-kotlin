package br.dev.s2w.alura.flix.gateway

import br.dev.s2w.alura.flix.domain.exception.VideoNotFoundException
import br.dev.s2w.alura.flix.domain.gateway.DeleteVideoById
import br.dev.s2w.alura.flix.gateway.repository.VideoRepository
import br.dev.s2w.alura.flix.infrastructure.utility.Constants
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.VIDEO_NOT_FOUND_EXCEPTION_MESSAGE
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Component

@Component
class DeleteVideoByIdImpl(
    private val videoRepository: VideoRepository
) : DeleteVideoById {

    override fun removeOne(id: Long) {
        try {
            videoRepository.deleteById(id)
        } catch (e: EmptyResultDataAccessException) {
            throw VideoNotFoundException(VIDEO_NOT_FOUND_EXCEPTION_MESSAGE)
        }
    }
}