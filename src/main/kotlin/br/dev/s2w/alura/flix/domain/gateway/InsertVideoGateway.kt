package br.dev.s2w.alura.flix.domain.gateway

import br.dev.s2w.alura.flix.domain.model.Video

interface InsertVideoGateway {
    fun saveOne(video: Video): Video
}