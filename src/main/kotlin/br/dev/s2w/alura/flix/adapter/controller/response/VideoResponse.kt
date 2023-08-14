package br.dev.s2w.alura.flix.adapter.controller.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class VideoResponse(
    var id: Long,

    var titulo: String,

    var descricao: String,

    var url: String
)