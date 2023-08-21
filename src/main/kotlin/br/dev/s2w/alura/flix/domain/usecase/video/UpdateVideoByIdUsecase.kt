package br.dev.s2w.alura.flix.domain.usecase.video

import br.dev.s2w.alura.flix.domain.model.Categoria
import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.domain.service.CategoriaService
import br.dev.s2w.alura.flix.domain.service.VideoService

class UpdateVideoByIdUsecase(
    private val videoService: VideoService,
    private val categoriaService: CategoriaService
) {

    fun execute(videoId: Long, categoriaId: Long?, video: Video): Video {
        val targetVideo = videoService.retriveOneBy(videoId)

        val targetCategoriaId = categoriaId.takeIf { it != null && it > 0L }
            ?: targetVideo.categoria?.id ?: 1L

        val targetCategoria = categoriaService.retrieveOneBy(targetCategoriaId)

        val videoToUpdate = buildVideo(video, targetCategoria)

        return videoService.modifyOneBy(videoId, videoToUpdate)
    }

    private fun buildVideo(video: Video, targetCategoria: Categoria): Video {
        return Video(
            titulo = video.titulo,
            descricao = video.descricao,
            url = video.url,
            categoria = targetCategoria
        )
    }
}