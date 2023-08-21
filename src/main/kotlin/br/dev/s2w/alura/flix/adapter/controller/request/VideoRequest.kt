package br.dev.s2w.alura.flix.adapter.controller.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.hibernate.validator.constraints.URL
import javax.validation.constraints.NotBlank

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class VideoRequest(
    val categoriaId: Long? = null,

    @field: NotBlank(message = "This field cannot be blank!")
    val titulo: String,

    @field: NotBlank(message = "This field cannot be blank!")
    val descricao: String,

    @field: NotBlank(message = "This field cannot be blank!")
    @field: URL(message = "This field does not have a valid url!")
    val url: String
)