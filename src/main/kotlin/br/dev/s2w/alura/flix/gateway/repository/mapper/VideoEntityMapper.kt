package br.dev.s2w.alura.flix.gateway.repository.mapper

import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.gateway.repository.entity.VideoEntity

object VideoEntityMapper {

    fun Video.toVideoEntity(): VideoEntity {
        return VideoEntity(id, titulo, descricao, url)
    }

    fun VideoEntity.toVideo(): Video {
        return Video(id, titulo, descricao, url)
    }
}