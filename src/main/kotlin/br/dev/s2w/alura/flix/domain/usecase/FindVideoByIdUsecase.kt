package br.dev.s2w.alura.flix.domain.usecase

import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.domain.service.VideoService

class FindVideoByIdUsecase(
    private val videoService: VideoService
) {

    fun execute(id: Long): Video {
        return videoService.retriveOneBy(id)
    }
}