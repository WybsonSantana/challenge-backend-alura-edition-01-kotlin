package br.dev.s2w.alura.flix.domain.usecase

import br.dev.s2w.alura.flix.domain.service.VideoService

class DeleteVideoByIdUsecase(
    private val videoService: VideoService
) {

    fun execute(id: Long) {
        videoService.removeOneBy(id)
    }
}