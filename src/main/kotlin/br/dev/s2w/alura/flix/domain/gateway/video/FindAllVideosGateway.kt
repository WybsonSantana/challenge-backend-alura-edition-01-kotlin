package br.dev.s2w.alura.flix.domain.gateway.video

import br.dev.s2w.alura.flix.domain.model.Video
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FindAllVideosGateway {
    fun fetch(pageable: Pageable): Page<Video>
}