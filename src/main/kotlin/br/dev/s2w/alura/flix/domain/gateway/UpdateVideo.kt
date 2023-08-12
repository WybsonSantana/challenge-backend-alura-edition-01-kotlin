package br.dev.s2w.alura.flix.domain.gateway

import br.dev.s2w.alura.flix.domain.model.Video

interface UpdateVideo {
    fun modifyOne(id: Long, video: Video): Video
}