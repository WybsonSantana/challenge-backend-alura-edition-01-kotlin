package br.dev.s2w.alura.flix.domain.usecase.video

import br.dev.s2w.alura.flix.domain.model.Categoria
import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.domain.service.CategoriaService
import br.dev.s2w.alura.flix.domain.service.VideoService

class InsertVideoUsecase(
    private val videoService: VideoService,
    private val categoriaService: CategoriaService
) {

    fun execute(categoriaId: Long?, video: Video): Video {
        val targetCategoriaId = categoriaId?.takeIf { it > 0L } ?: 1L

        val categoria = categoriaService.retrieveOneBy(targetCategoriaId)

        val videoToInsert = buildVideo(video, categoria)

        return videoService.saveOne(videoToInsert)
    }

    private fun buildVideo(video: Video, categoria: Categoria): Video {
        return Video(
            titulo = video.titulo,
            descricao = video.descricao,
            url = video.url,
            categoria = categoria
        )
    }
}