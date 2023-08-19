package br.dev.s2w.alura.flix.adapter.controller.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class VideoResponse(
    val id: Long,

    val titulo: String,

    val descricao: String,

    val url: String
)