package br.dev.s2w.alura.flix.adapter.api

import br.dev.s2w.alura.flix.adapter.controller.response.VideoResponse
import org.springframework.http.ResponseEntity

interface VideoAPI {
    fun findAllVideos(): List<VideoResponse>
    fun findVideoById(id: Long): ResponseEntity<VideoResponse>
}