package br.dev.s2w.alura.flix.adapter.controller

import br.dev.s2w.alura.flix.adapter.api.VideoAPI
import br.dev.s2w.alura.flix.adapter.controller.mapper.VideoMapper.toVideo
import br.dev.s2w.alura.flix.adapter.controller.mapper.VideoMapper.toVideoResponse
import br.dev.s2w.alura.flix.adapter.controller.request.VideoRequest
import br.dev.s2w.alura.flix.adapter.controller.response.VideoResponse
import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.domain.usecase.FindAllVideosUsecase
import br.dev.s2w.alura.flix.domain.usecase.FindVideoByIdUsecase
import br.dev.s2w.alura.flix.domain.usecase.InsertVideoUsecase
import br.dev.s2w.alura.flix.domain.usecase.UpdateVideoUsecase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/api/v1/videos")
class VideoController(
    private val findAllVideosUsecase: FindAllVideosUsecase,
    private val findVideoByIdUsecase: FindVideoByIdUsecase,
    private val insertVideoUsecase: InsertVideoUsecase,
    private val updateVideoUsecase: UpdateVideoUsecase
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

    @PostMapping
    override fun insertVideo(
        @RequestBody videoRequest: VideoRequest,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<VideoResponse> {
        insertVideoUsecase.execute(videoRequest.toVideo()).also {
            val uri = buildVideoUri(it, uriBuilder)
            return ResponseEntity.created(uri).body(it.toVideoResponse())
        }
    }

    @PutMapping("/{id}")
    override fun updateVideo(
        @PathVariable id: Long,
        @RequestBody videoRequest: VideoRequest
    ): ResponseEntity<VideoResponse> {
        updateVideoUsecase.execute(id, videoRequest.toVideo()).also {
            return ResponseEntity.ok(it.toVideoResponse())
        }
    }

    private fun buildVideoUri(createdVideo: Video, uriBuilder: UriComponentsBuilder): URI {
        return uriBuilder.path("/api/v1/videos/${createdVideo.id}").build().toUri()
    }
}