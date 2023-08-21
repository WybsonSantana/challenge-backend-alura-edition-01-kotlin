package br.dev.s2w.alura.flix.domain.model

data class Video(
    val id: Long? = null,

    val titulo: String,

    val descricao: String,

    val url: String,

    val categoria: Categoria? = null
)