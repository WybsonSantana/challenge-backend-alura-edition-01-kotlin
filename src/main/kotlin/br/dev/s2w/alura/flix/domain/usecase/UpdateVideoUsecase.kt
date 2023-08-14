package br.dev.s2w.alura.flix.domain.usecase

import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.domain.service.VideoService

class UpdateVideoUsecase(
    private val videoService: VideoService
) {

    fun execute(id: Long, video: Video): Video {
        return videoService.modifyOneBy(id, video)
    }
}