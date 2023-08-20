package br.dev.s2w.alura.flix.gateway.impl.categoria

import br.dev.s2w.alura.flix.domain.exception.CategoriaNotFoundException
import br.dev.s2w.alura.flix.domain.gateway.categoria.DeleteCategoriaByIdGateway
import br.dev.s2w.alura.flix.gateway.repository.CategoriaRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Component

@Component
class DeleteCategoriaByIdGatewayImpl(
    private val categoriaRepository: CategoriaRepository
) : DeleteCategoriaByIdGateway {

    override fun removeOneBy(id: Long) {
        try {
            categoriaRepository.deleteById(id)
        } catch (e: EmptyResultDataAccessException) {
            throw CategoriaNotFoundException(br.dev.s2w.alura.flix.infrastructure.utility.Constants.CATEGORIA_NOT_FOUND_EXCEPTION_MESSAGE)
        }
    }
}