package br.dev.s2w.alura.flix.domain.gateway.categoria

import br.dev.s2w.alura.flix.domain.model.Categoria

interface UpdateCategoriaByIdGateway {
    fun modifyOne(id: Long, categoria: Categoria): Categoria
}