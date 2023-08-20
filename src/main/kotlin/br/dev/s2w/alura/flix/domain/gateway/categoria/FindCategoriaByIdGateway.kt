package br.dev.s2w.alura.flix.domain.gateway.categoria

import br.dev.s2w.alura.flix.domain.model.Categoria

interface FindCategoriaByIdGateway {
    fun fetchOneBy(id: Long): Categoria
}