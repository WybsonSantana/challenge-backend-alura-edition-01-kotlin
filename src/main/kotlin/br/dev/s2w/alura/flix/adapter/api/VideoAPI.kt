package br.dev.s2w.alura.flix.adapter.api

import br.dev.s2w.alura.flix.adapter.controller.response.VideoResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/api/v1/videos")
interface VideoAPI {

    @GetMapping
    fun findAllVideos(): List<VideoResponse>
}