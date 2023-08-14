package br.dev.s2w.alura.flix.adapter.controller.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDateTime

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ErrorFieldResponse(
    val timeStamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String,
    val validationError: ValidationError,
    val path: String
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ValidationError(
    val fields: Map<String, String?>,
)