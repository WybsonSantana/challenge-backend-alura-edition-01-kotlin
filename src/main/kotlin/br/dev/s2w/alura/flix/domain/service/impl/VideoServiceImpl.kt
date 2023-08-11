package br.dev.s2w.alura.flix.domain.service.impl

import br.dev.s2w.alura.flix.domain.gateway.FindAllVideos
import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.domain.service.VideoService

class VideoServiceImpl(
    private val findAllVideos: FindAllVideos
) : VideoService {

    override fun retrieve(): List<Video> {
        return findAllVideos.fetch()
    }
}