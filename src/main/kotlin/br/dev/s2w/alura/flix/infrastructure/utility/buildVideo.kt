package br.dev.s2w.alura.flix.infrastructure.utility

import br.dev.s2w.alura.flix.domain.model.Categoria
import br.dev.s2w.alura.flix.domain.model.Video

fun buildVideo(video: Video, categoriaToInsert: Categoria): Video {
    return Video(
        titulo = video.titulo,
        descricao = video.descricao,
        url = video.url,
        categoria = categoriaToInsert
    )
}