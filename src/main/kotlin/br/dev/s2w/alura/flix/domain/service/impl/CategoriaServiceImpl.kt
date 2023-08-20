package br.dev.s2w.alura.flix.domain.service.impl

import br.dev.s2w.alura.flix.domain.gateway.categoria.*
import br.dev.s2w.alura.flix.domain.model.Categoria
import br.dev.s2w.alura.flix.domain.service.CategoriaService

class CategoriaServiceImpl(
    private val findAllCategoriasGateway: FindAllCategoriasGateway,
    private val findCategoriaByIdGateway: FindCategoriaByIdGateway,
    private val insertCategoriaGateway: InsertCategoriaGateway,
    private val updateCategoriaByIdGateway: UpdateCategoriaByIdGateway,
    private val deleteCategoriaByIdGateway: DeleteCategoriaByIdGateway
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

    override fun modifyOneBy(id: Long, categoria: Categoria): Categoria {
        return updateCategoriaByIdGateway.modifyOne(id, categoria)
    }

    override fun removeOneBy(id: Long) {
        deleteCategoriaByIdGateway.removeOneBy(id)
    }
}