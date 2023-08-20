package br.dev.s2w.alura.flix.gateway.impl.categoria

import br.dev.s2w.alura.flix.domain.exception.CategoriaNotFoundException
import br.dev.s2w.alura.flix.domain.gateway.categoria.FindCategoriaByIdGateway
import br.dev.s2w.alura.flix.domain.model.Categoria
import br.dev.s2w.alura.flix.gateway.repository.CategoriaRepository
import br.dev.s2w.alura.flix.gateway.repository.mapper.CategoriaEntityMapper.toCategoria
import br.dev.s2w.alura.flix.infrastructure.utility.Constants
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.CATEGORIA_NOT_FOUND_EXCEPTION_MESSAGE
import org.springframework.stereotype.Component

@Component
class FindCategoriaByIdGatewayImpl(
    private val categoriaRepository: CategoriaRepository
) : FindCategoriaByIdGateway {

    override fun fetchOneBy(id: Long): Categoria {
        try {
            return categoriaRepository.findById(id).get().toCategoria()
        } catch (e: NoSuchElementException) {
            throw CategoriaNotFoundException(CATEGORIA_NOT_FOUND_EXCEPTION_MESSAGE)
        }
    }
}