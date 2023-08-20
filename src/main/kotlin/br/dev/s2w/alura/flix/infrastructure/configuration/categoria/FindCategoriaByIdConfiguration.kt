package br.dev.s2w.alura.flix.infrastructure.configuration.categoria

import br.dev.s2w.alura.flix.domain.service.CategoriaService
import br.dev.s2w.alura.flix.domain.usecase.categoria.FindCategoriaByIdUsecase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FindCategoriaByIdConfiguration {

    @Bean
    fun findCategoriaByIdUsecase(categoriaService: CategoriaService): FindCategoriaByIdUsecase {
        return FindCategoriaByIdUsecase(categoriaService)
    }
}