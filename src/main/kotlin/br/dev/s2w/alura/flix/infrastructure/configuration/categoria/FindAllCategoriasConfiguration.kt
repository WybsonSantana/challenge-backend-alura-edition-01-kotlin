package br.dev.s2w.alura.flix.infrastructure.configuration.categoria

import br.dev.s2w.alura.flix.domain.service.CategoriaService
import br.dev.s2w.alura.flix.domain.usecase.categoria.FindAllCategoriasUsecase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FindAllCategoriasConfiguration {

    @Bean
    fun findAllCategoriasUsecase(categoriaService: CategoriaService): FindAllCategoriasUsecase {
        return FindAllCategoriasUsecase(categoriaService)
    }
}