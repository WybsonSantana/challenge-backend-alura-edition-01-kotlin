package br.dev.s2w.alura.flix.domain.service.impl

import br.dev.s2w.alura.flix.domain.gateway.categoria.FindAllCategoriasGateway
import br.dev.s2w.alura.flix.domain.gateway.categoria.FindCategoriaByIdGateway
import br.dev.s2w.alura.flix.domain.gateway.categoria.InsertCategoriaGateway
import br.dev.s2w.alura.flix.domain.model.Categoria
import br.dev.s2w.alura.flix.domain.service.CategoriaService

class CategoriaServiceImpl(
    private val findAllCategoriasGateway: FindAllCategoriasGateway,
    private val findCategoriaByIdGateway: FindCategoriaByIdGateway,
    private val insertCategoriaGateway: InsertCategoriaGateway
) : CategoriaService {

    override fun retrieve(): List<Categoria> {
        return findAllCategoriasGateway.fetch()
    }

    override fun retrieveOneBy(id: Long): Categoria {
        return findCategoriaByIdGateway.fetchOneBy(id)
    }

    override fun saveOne(categoria: Categoria): Categoria {
        return insertCategoriaGateway.saveOne(categoria)
    }
}