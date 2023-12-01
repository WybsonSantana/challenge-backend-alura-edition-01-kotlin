package br.dev.s2w.alura.flix.domain.model

import br.dev.s2w.alura.flix.infrastructure.annotation.NoArg

@NoArg
data class Categoria(
    val id: Long? = null,

    val titulo: String,

    val cor: String
)