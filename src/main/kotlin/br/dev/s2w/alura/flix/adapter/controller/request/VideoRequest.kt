package br.dev.s2w.alura.flix.adapter.controller.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.validator.constraints.URL
import javax.validation.constraints.NotBlank

@JsonIgnoreProperties(ignoreUnknown = true)
data class VideoRequest(
    @field: NotBlank(message = "This field cannot be blank!")
    var titulo: String,

    @field: NotBlank(message = "This field cannot be blank!")
    var descricao: String,

    @field: NotBlank(message = "This field cannot be blank!")
    @field: URL(message = "This field does not have a valid url!")
    var url: String
)