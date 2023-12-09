package br.dev.s2w.alura.flix.domain.usecase.video

import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.domain.service.CategoriaService
import br.dev.s2w.alura.flix.domain.service.VideoService
import br.dev.s2w.alura.flix.infrastructure.utility.buildVideo

class InsertVideoUsecase(
    private val videoService: VideoService,
    private val categoriaService: CategoriaService
) {

    fun execute(categoriaId: Long?, video: Video): Video {
        val targetCategoriaId = categoriaId?.takeIf { it > 0L } ?: 1L

        val categoriaToInsert = categoriaService.retrieveOneBy(targetCategoriaId)

        val videoToInsert = buildVideo(video, categoriaToInsert)

        return videoService.saveOne(videoToInsert)
    }
}