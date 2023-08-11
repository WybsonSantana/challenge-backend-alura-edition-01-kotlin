package br.dev.s2w.alura.flix.domain.service

import br.dev.s2w.alura.flix.domain.model.Video

interface VideoService {
    fun retrieve(): List<Video>
}