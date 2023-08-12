package br.dev.s2w.alura.flix.domain.gateway

import br.dev.s2w.alura.flix.domain.model.Video

interface FindAllVideos {
    fun fetch(): List<Video>
}