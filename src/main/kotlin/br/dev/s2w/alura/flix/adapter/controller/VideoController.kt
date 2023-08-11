package br.dev.s2w.alura.flix.adapter.controller

import br.dev.s2w.alura.flix.adapter.api.VideoAPI
import br.dev.s2w.alura.flix.adapter.controller.mapper.VideoMapper.toVideoResponse
import br.dev.s2w.alura.flix.adapter.controller.response.VideoResponse
import br.dev.s2w.alura.flix.domain.usecase.FindAllVideosUsecase
import org.springframework.web.bind.annotation.RestController

@RestController
class VideoController(
    private val findAllVideosUsecase: FindAllVideosUsecase
) : VideoAPI {

    override fun findAllVideos(): List<VideoResponse> {
        return findAllVideosUsecase.execute().map { it.toVideoResponse() }
    }
}