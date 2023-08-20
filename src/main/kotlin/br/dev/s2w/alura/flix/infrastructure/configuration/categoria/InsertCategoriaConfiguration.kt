package br.dev.s2w.alura.flix.infrastructure.configuration.categoria

import br.dev.s2w.alura.flix.domain.service.CategoriaService
import br.dev.s2w.alura.flix.domain.usecase.categoria.InsertCategoriaUsecase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InsertCategoriaConfiguration {

    @Bean
    fun insertCategoriaUsecase(categoriaService: CategoriaService): InsertCategoriaUsecase {
        return InsertCategoriaUsecase(categoriaService)
    }
}