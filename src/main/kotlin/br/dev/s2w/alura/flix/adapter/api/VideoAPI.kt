package br.dev.s2w.alura.flix.adapter.api

import br.dev.s2w.alura.flix.adapter.controller.request.VideoRequest
import br.dev.s2w.alura.flix.adapter.controller.response.VideoResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.util.UriComponentsBuilder

interface VideoAPI {
    fun findAllVideos(): List<VideoResponse>
    fun findVideoById(id: Long): ResponseEntity <VideoResponse>
    fun insertVideo(videoRequest: VideoRequest, uriBuilder: UriComponentsBuilder): ResponseEntity<VideoResponse>
    fun updateVideo(id: Long, videoRequest: VideoRequest): ResponseEntity<VideoResponse>
}