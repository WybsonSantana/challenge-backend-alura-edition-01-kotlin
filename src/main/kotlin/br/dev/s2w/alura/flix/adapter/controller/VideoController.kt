package br.dev.s2w.alura.flix.adapter.controller

import br.dev.s2w.alura.flix.adapter.api.VideoAPI
import br.dev.s2w.alura.flix.adapter.controller.mapper.VideoMapper.toVideo
import br.dev.s2w.alura.flix.adapter.controller.mapper.VideoMapper.toVideoResponse
import br.dev.s2w.alura.flix.adapter.controller.request.VideoRequest
import br.dev.s2w.alura.flix.adapter.controller.response.VideoResponse
import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.domain.usecase.*
import br.dev.s2w.alura.flix.domain.usecase.video.*
import br.dev.s2w.alura.flix.gateway.repository.VideoRepository
import br.dev.s2w.alura.flix.gateway.repository.mapper.VideoEntityMapper.toVideo
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.VIDEO_V1_API_PATH
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping(VIDEO_V1_API_PATH)
class VideoController(
    private val findAllVideosUsecase: FindAllVideosUsecase,
    private val findAllVideosByCategoriaUsecase: FindAllVideosByCategoriaUsecase,
    private val findAllVideosByTituloUsecase: FindAllVideosByTituloUsecase,
    private val findVideoByIdUsecase: FindVideoByIdUsecase,
    private val insertVideoUsecase: InsertVideoUsecase,
    private val updateVideoByIdUsecase: UpdateVideoByIdUsecase,
    private val deleteVideoByIdUsecase: DeleteVideoByIdUsecase
) : VideoAPI {

    @GetMapping
    override fun findAllVideos(): List<VideoResponse> {
        return findAllVideosUsecase.execute().map { it.toVideoResponse() }
    }

    @GetMapping("/categoria/{categoriaId}")
    override fun findAllVideosByCategoria(@PathVariable categoriaId: Long): List<VideoResponse> {
        return findAllVideosByCategoriaUsecase.execute(categoriaId).map { it.toVideoResponse() }
    }

    @GetMapping("/search")
    override fun findAllVideosByTitulo(@RequestParam titulo: String): List<VideoResponse> {
        return findAllVideosByTituloUsecase.execute(titulo).map { it.toVideoResponse() }
    }

    @GetMapping("/{videoId}")
    override fun findVideoById(@PathVariable videoId: Long): ResponseEntity<VideoResponse> {
        val videoResponse = findVideoByIdUsecase.execute(videoId).toVideoResponse()

        return ResponseEntity.ok(videoResponse)
    }

    @PostMapping
    override fun insertVideo(
        @RequestBody @Valid videoRequest: VideoRequest,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<VideoResponse> {
        val categoriaId = videoRequest.categoriaId
        val video = videoRequest.toVideo()

        insertVideoUsecase.execute(categoriaId, video).also {
            val uri = buildVideoUri(it, uriBuilder)
            return ResponseEntity.created(uri).body(it.toVideoResponse())
        }
    }

    @PutMapping("/{videoId}")
    override fun updateVideo(
        @PathVariable videoId: Long,
        @RequestBody @Valid videoRequest: VideoRequest
    ): ResponseEntity<VideoResponse> {
        val categoriaId = videoRequest.categoriaId
        val video = videoRequest.toVideo()

        updateVideoByIdUsecase.execute(videoId, categoriaId, video).also {
            return ResponseEntity.ok(it.toVideoResponse())
        }
    }

    @DeleteMapping("/{videoId}")
    override fun deleteVideoById(@PathVariable videoId: Long): ResponseEntity<Unit> {
        deleteVideoByIdUsecase.execute(videoId).also {
            return ResponseEntity.noContent().build()
        }
    }

    private fun buildVideoUri(createdVideo: Video, uriBuilder: UriComponentsBuilder): URI {
        val videoId = createdVideo.id
        val videoApiPath = VIDEO_V1_API_PATH
        val videoUri = uriBuilder
            .path("/$videoApiPath/$videoId")
            .build()
            .toUri()

        return videoUri
    }
}