package br.dev.s2w.alura.flix.infrastructure.configuration.categoria

import br.dev.s2w.alura.flix.domain.gateway.categoria.*
import br.dev.s2w.alura.flix.domain.service.CategoriaService
import br.dev.s2w.alura.flix.domain.service.impl.CategoriaServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CategoriaServiceConfiguration {

    @Bean
    fun categoriaService(
        findAllCategoriasGateway: FindAllCategoriasGateway,
        findCategoriaByIdGateway: FindCategoriaByIdGateway,
        insertCategoriaGateway: InsertCategoriaGateway,
        updateCategoriaByIdGateway: UpdateCategoriaByIdGateway,
        deleteCategoriaByIdGateway: DeleteCategoriaByIdGateway
    ): CategoriaService {
        return CategoriaServiceImpl(
            findAllCategoriasGateway,
            findCategoriaByIdGateway,
            insertCategoriaGateway,
            updateCategoriaByIdGateway,
            deleteCategoriaByIdGateway
        )
    }
}