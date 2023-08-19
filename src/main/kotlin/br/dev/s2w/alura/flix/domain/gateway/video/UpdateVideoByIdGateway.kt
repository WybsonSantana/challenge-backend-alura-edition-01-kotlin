package br.dev.s2w.alura.flix.domain.gateway.video

import br.dev.s2w.alura.flix.domain.model.Video

interface UpdateVideoByIdGateway {
    fun modifyOneBy(id: Long, video: Video): Video
}