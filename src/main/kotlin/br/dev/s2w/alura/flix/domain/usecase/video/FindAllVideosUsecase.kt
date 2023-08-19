package br.dev.s2w.alura.flix.domain.usecase.video

import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.domain.service.VideoService

class FindAllVideosUsecase(
    private val videoService: VideoService
) {

    fun execute(): List<Video> {
        return videoService.retrieve()
    }
}