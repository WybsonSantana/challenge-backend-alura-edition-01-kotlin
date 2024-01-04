package br.dev.s2w.alura.flix.adapter.api

import br.dev.s2w.alura.flix.adapter.controller.request.VideoRequest
import br.dev.s2w.alura.flix.adapter.controller.response.VideoResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.util.UriComponentsBuilder

interface VideoAPI {
    fun findAllVideos(pageable: Pageable): Page<VideoResponse>

    fun findAllVideosByCategoria(categoriaId: Long, pageable: Pageable): Page<VideoResponse>

    fun findAllVideosByTitulo(titulo: String, pageable: Pageable): Page<VideoResponse>

    fun findVideoById(videoId: Long): ResponseEntity<VideoResponse>

    fun insertVideo(videoRequest: VideoRequest, uriBuilder: UriComponentsBuilder): ResponseEntity<VideoResponse>

    fun updateVideo(videoId: Long, videoRequest: VideoRequest): ResponseEntity<VideoResponse>

    fun deleteVideoById(videoId: Long): ResponseEntity<Unit>
}