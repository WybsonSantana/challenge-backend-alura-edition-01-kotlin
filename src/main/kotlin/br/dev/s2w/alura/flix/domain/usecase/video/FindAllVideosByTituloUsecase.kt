package br.dev.s2w.alura.flix.domain.usecase.video

import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.domain.service.VideoService

class FindAllVideosByTituloUsecase(
    private val videoService: VideoService
) {

    fun execute(titulo: String): List<Video> {
        return videoService.retriveAllByTitulo(titulo)
    }
}