package br.dev.s2w.alura.flix.domain.usecase.video

import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.domain.service.VideoService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

class FindAllVideosByTituloUsecase(
    private val videoService: VideoService
) {

    fun execute(titulo: String, pageable: Pageable): Page<Video> {
        return videoService.retriveAllByTitulo(titulo, pageable)
    }
}