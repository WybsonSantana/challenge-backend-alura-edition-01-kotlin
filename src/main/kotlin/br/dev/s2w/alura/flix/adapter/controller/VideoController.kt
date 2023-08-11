package br.dev.s2w.alura.flix.adapter.controller

import br.dev.s2w.alura.flix.adapter.api.VideoAPI
import br.dev.s2w.alura.flix.adapter.controller.mapper.VideoMapper.toVideoResponse
import br.dev.s2w.alura.flix.adapter.controller.response.VideoResponse
import br.dev.s2w.alura.flix.domain.usecase.FindAllVideosUsecase
import br.dev.s2w.alura.flix.domain.usecase.FindVideoByIdUsecase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/videos")
class VideoController(
    private val findAllVideosUsecase: FindAllVideosUsecase,
    private val findVideoByIdUsecase: FindVideoByIdUsecase
) : VideoAPI {

    @GetMapping
    override fun findAllVideos(): List<VideoResponse> {
        return findAllVideosUsecase.execute().map { it.toVideoResponse() }
    }

    @GetMapping("/{id}")
    override fun findVideoById(@PathVariable id: Long): ResponseEntity<VideoResponse> {
        val videoResponse = findVideoByIdUsecase.execute(id).toVideoResponse()
        return ResponseEntity.ok(videoResponse)
    }
}