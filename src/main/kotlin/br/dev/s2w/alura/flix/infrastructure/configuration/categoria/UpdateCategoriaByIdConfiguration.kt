package br.dev.s2w.alura.flix.infrastructure.configuration.categoria

import br.dev.s2w.alura.flix.domain.service.CategoriaService
import br.dev.s2w.alura.flix.domain.usecase.categoria.UpdateCategoriaUsecase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UpdateCategoriaByIdConfiguration {

    @Bean
    fun updateCategoriaByIdUsecase(categoriaService: CategoriaService): UpdateCategoriaUsecase {
        return UpdateCategoriaUsecase(categoriaService)
    }
}