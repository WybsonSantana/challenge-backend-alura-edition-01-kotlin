package br.dev.s2w.alura.flix.gateway.impl.categoria

import br.dev.s2w.alura.flix.domain.gateway.categoria.FindAllCategoriasGateway
import br.dev.s2w.alura.flix.domain.model.Categoria
import br.dev.s2w.alura.flix.gateway.repository.CategoriaRepository
import br.dev.s2w.alura.flix.gateway.repository.mapper.CategoriaEntityMapper.toCategoria
import org.springframework.stereotype.Component

@Component
class FindAllCategoriasGatewayImpl(
    private val categoriaRepository: CategoriaRepository
) : FindAllCategoriasGateway {

    override fun fetch(): List<Categoria> {
        return categoriaRepository.findAll().map { it.toCategoria() }
    }
}