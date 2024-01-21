package br.dev.s2w.alura.flix.domain.model

import br.dev.s2w.alura.flix.infrastructure.annotation.NoArg

@NoArg
data class Usuario(
    val id: Long? = null,

    val nome: String,

    val email: String,

    val password: String
)