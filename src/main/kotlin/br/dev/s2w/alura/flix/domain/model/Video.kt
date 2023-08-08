package br.dev.s2w.alura.flix.domain.model

data class Video(
    var id: Long,

    var titulo: String,

    var descricao: String,

    var url: String
)