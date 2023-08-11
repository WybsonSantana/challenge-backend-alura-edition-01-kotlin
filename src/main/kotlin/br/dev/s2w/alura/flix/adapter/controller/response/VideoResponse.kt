package br.dev.s2w.alura.flix.adapter.controller.response

data class VideoResponse(
    var id: Long,

    var titulo: String,

    var descricao: String,

    var url: String
)