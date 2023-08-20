package br.dev.s2w.alura.flix.adapter.api

import br.dev.s2w.alura.flix.adapter.controller.response.CategoriaResponse
import org.springframework.http.ResponseEntity

interface CategoriaAPI {
    fun findAllCategorias(): List<CategoriaResponse>

    fun findCategoriaById(id: Long): ResponseEntity<CategoriaResponse>
}