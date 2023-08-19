package br.dev.s2w.alura.flix.domain.service.impl

import br.dev.s2w.alura.flix.domain.gateway.FindAllCategoriasGateway
import br.dev.s2w.alura.flix.domain.model.Categoria
import br.dev.s2w.alura.flix.domain.service.CategoriaService

class CategoriaServiceImpl(
    private val findAllCategoriasGateway: FindAllCategoriasGateway
) : CategoriaService {

    override fun retrieve(): List<Categoria> {
        return findAllCategoriasGateway.fetch()
    }
}