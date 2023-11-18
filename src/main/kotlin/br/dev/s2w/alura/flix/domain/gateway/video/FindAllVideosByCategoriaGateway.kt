package br.dev.s2w.alura.flix.domain.gateway.video

import br.dev.s2w.alura.flix.domain.model.Video

interface FindAllVideosByCategoriaGateway {
    fun fetchByCategoria(id: Long): List<Video>
}