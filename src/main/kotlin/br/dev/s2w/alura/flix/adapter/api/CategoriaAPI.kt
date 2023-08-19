package br.dev.s2w.alura.flix.adapter.api

import br.dev.s2w.alura.flix.adapter.controller.response.CategoriaResponse

interface CategoriaAPI {
    fun findAllCategorias(): List<CategoriaResponse>
}