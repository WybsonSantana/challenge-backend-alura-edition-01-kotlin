package br.dev.s2w.alura.flix.gateway

import br.dev.s2w.alura.flix.domain.gateway.DeleteVideoById
import br.dev.s2w.alura.flix.gateway.repository.VideoRepository
import org.springframework.stereotype.Component

@Component
class DeleteVideoByIdImpl(
    private val videoRepository: VideoRepository
) : DeleteVideoById {

    override fun removeOne(id: Long) {
        videoRepository.deleteById(id)
    }
}