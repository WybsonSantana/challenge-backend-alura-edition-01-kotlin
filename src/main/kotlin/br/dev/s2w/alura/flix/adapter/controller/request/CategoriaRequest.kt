package br.dev.s2w.alura.flix.adapter.controller.request

import javax.validation.constraints.NotBlank

data class CategoriaRequest(
    @field: NotBlank(message = "This field cannot be blank!")
    val titulo: String,

    @field: NotBlank(message = "This field cannot be blank!")
    val cor: String
)