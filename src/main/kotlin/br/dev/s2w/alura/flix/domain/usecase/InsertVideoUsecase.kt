package br.dev.s2w.alura.flix.domain.usecase

import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.domain.service.VideoService

class InsertVideoUsecase(
    private val videoService: VideoService
) {

    fun execute(video: Video): Video {
        return videoService.saveOne(video)
    }
}