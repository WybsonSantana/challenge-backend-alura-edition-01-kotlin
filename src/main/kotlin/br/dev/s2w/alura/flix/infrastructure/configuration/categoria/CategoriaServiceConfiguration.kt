package br.dev.s2w.alura.flix.infrastructure.configuration.categoria

import br.dev.s2w.alura.flix.domain.gateway.categoria.FindAllCategoriasGateway
import br.dev.s2w.alura.flix.domain.gateway.categoria.FindCategoriaByIdGateway
import br.dev.s2w.alura.flix.domain.service.CategoriaService
import br.dev.s2w.alura.flix.domain.service.impl.CategoriaServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CategoriaServiceConfiguration {

    @Bean
    fun categoriaService(
        findAllCategoriasGateway: FindAllCategoriasGateway,
        findCategoriaByIdGateway: FindCategoriaByIdGateway
    ): CategoriaService {
        return CategoriaServiceImpl(
            findAllCategoriasGateway,
            findCategoriaByIdGateway
        )
    }
}