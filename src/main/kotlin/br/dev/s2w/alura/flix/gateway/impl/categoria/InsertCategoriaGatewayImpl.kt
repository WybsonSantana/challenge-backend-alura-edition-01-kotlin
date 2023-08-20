package br.dev.s2w.alura.flix.gateway.impl.categoria

import br.dev.s2w.alura.flix.domain.gateway.categoria.InsertCategoriaGateway
import br.dev.s2w.alura.flix.domain.model.Categoria
import br.dev.s2w.alura.flix.gateway.repository.CategoriaRepository
import br.dev.s2w.alura.flix.gateway.repository.mapper.CategoriaEntityMapper.toCategoria
import br.dev.s2w.alura.flix.gateway.repository.mapper.CategoriaEntityMapper.toCategoriaEntity
import org.springframework.stereotype.Component

@Component
class InsertCategoriaGatewayImpl(
    private val categoriaRepository: CategoriaRepository
) : InsertCategoriaGateway {

    override fun saveOne(categoria: Categoria): Categoria {
        categoriaRepository.save(categoria.toCategoriaEntity()).also {
            return it.toCategoria()
        }
    }
}