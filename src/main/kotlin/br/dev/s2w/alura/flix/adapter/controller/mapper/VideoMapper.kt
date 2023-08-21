package br.dev.s2w.alura.flix.adapter.controller.mapper

import br.dev.s2w.alura.flix.adapter.controller.request.VideoRequest
import br.dev.s2w.alura.flix.adapter.controller.response.VideoResponse
import br.dev.s2w.alura.flix.domain.model.Video

object VideoMapper {

    fun Video.toVideoResponse(): VideoResponse {
        return VideoResponse(id!!, categoria?.id!!, titulo, descricao, url)
    }

    fun VideoRequest.toVideo(): Video {
        return Video(titulo = titulo, descricao = descricao, url = url)
    }
}