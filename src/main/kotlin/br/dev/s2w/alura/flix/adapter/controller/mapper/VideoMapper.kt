package br.dev.s2w.alura.flix.adapter.controller.mapper

import br.dev.s2w.alura.flix.adapter.controller.response.VideoResponse
import br.dev.s2w.alura.flix.domain.model.Video

object VideoMapper {

    fun Video.toVideoResponse(): VideoResponse {
        return VideoResponse(this.id, this.titulo, this.descricao, this.url)
    }
}