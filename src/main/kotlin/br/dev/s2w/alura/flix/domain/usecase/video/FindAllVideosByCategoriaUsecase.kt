package br.dev.s2w.alura.flix.domain.usecase.video

import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.domain.service.VideoService

class FindAllVideosByCategoriaUsecase(
    private val videoService: VideoService
) {

    fun execute(id: Long): List<Video> {
        return videoService.retriveAllByCategoria(id)
    }
}