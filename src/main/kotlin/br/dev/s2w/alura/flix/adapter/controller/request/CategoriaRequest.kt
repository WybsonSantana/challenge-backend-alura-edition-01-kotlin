package br.dev.s2w.alura.flix.adapter.controller.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.validation.constraints.NotBlank

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CategoriaRequest(
    @field: NotBlank(message = "This field cannot be blank!")
    val titulo: String,

    @field: NotBlank(message = "This field cannot be blank!")
    val cor: String
)