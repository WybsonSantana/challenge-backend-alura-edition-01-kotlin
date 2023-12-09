package br.dev.s2w.alura.flix.domain.usecase.video

import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.domain.service.CategoriaService
import br.dev.s2w.alura.flix.domain.service.VideoService
import br.dev.s2w.alura.flix.infrastructure.utility.buildVideo

class UpdateVideoByIdUsecase(
    private val videoService: VideoService,
    private val categoriaService: CategoriaService
) {

    fun execute(videoId: Long, categoriaId: Long?, video: Video): Video {
        val targetVideo = videoService.retriveOneBy(videoId)

        val targetCategoriaId = categoriaId.takeIf { it != null && it > 0L }
            ?: targetVideo.categoria?.id ?: 1L

        val categoriaToInsert = categoriaService.retrieveOneBy(targetCategoriaId)

        val videoToUpdate = buildVideo(video, categoriaToInsert)

        return videoService.modifyOneBy(videoId, videoToUpdate)
    }
}