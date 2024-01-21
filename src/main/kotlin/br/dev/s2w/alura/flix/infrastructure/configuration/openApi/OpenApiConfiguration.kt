package br.dev.s2w.alura.flix.infrastructure.configuration.openApi

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springframework.context.annotation.Configuration

@Configuration
@SecurityScheme(
    name = "Basic Auth",
    type = SecuritySchemeType.HTTP,
    scheme = "Basic"
)
@OpenAPIDefinition(
    info = Info(title = "Challenge Back-end Alura - Edition 01"),
    security = [SecurityRequirement(name = "Basic Auth")]
)
class OpenApiConfiguration