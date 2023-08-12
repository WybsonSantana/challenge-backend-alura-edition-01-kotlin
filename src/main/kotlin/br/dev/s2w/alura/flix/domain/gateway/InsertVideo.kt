package br.dev.s2w.alura.flix.domain.gateway

import br.dev.s2w.alura.flix.domain.model.Video

interface InsertVideo {
    fun saveOne(video: Video): Video
}