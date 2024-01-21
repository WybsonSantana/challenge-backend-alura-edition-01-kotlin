package br.dev.s2w.alura.flix.adapter.security.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UsuarioDTO(
    val username: String,

    val password: String
)