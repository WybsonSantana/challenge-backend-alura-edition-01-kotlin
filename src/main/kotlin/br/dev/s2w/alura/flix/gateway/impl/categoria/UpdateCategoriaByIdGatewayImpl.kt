package br.dev.s2w.alura.flix.gateway.impl.categoria

import br.dev.s2w.alura.flix.domain.exception.CategoriaNotFoundException
import br.dev.s2w.alura.flix.domain.gateway.categoria.UpdateCategoriaByIdGateway
import br.dev.s2w.alura.flix.domain.model.Categoria
import br.dev.s2w.alura.flix.gateway.repository.CategoriaRepository
import br.dev.s2w.alura.flix.gateway.repository.entity.CategoriaEntity
import br.dev.s2w.alura.flix.gateway.repository.mapper.CategoriaEntityMapper.toCategoria
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.CATEGORIA_NOT_FOUND_EXCEPTION_MESSAGE
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.stereotype.Component

@Component
class UpdateCategoriaByIdGatewayImpl(
    private val categoriaRepository: CategoriaRepository
) : UpdateCategoriaByIdGateway {

    override fun modifyOne(id: Long, categoria: Categoria): Categoria {
        val referencedCategoria = try {
            categoriaRepository.getReferenceById(id)
        } catch (e: JpaObjectRetrievalFailureException) {
            throw CategoriaNotFoundException(CATEGORIA_NOT_FOUND_EXCEPTION_MESSAGE)
        }

        val updatedCategoria = buildUpdatedCategoria(referencedCategoria, categoria)
        return categoriaRepository.save(updatedCategoria).toCategoria()
    }

    private fun buildUpdatedCategoria(referencedCategoria: CategoriaEntity, categoria: Categoria): CategoriaEntity {
        return referencedCategoria.copy(
            titulo = categoria.titulo,
            cor = categoria.cor
        )
    }
}