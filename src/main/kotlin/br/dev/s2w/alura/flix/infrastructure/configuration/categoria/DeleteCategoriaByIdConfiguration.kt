package br.dev.s2w.alura.flix.infrastructure.configuration.categoria

import br.dev.s2w.alura.flix.domain.service.CategoriaService
import br.dev.s2w.alura.flix.domain.usecase.categoria.DeleteCategoriaByIdUsecase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DeleteCategoriaByIdConfiguration {

    @Bean
    fun deleteCategoriaByIdUsecase(categoriaService: CategoriaService): DeleteCategoriaByIdUsecase {
        return DeleteCategoriaByIdUsecase(categoriaService)
    }
}