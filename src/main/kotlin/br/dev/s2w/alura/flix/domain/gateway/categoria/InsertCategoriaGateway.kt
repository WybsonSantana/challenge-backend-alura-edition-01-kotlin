package br.dev.s2w.alura.flix.domain.gateway.categoria

import br.dev.s2w.alura.flix.domain.model.Categoria

interface InsertCategoriaGateway {
    fun saveOne(categoria: Categoria): Categoria
}