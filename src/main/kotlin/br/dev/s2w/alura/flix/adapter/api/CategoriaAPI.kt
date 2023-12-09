package br.dev.s2w.alura.flix.adapter.api

import br.dev.s2w.alura.flix.adapter.controller.request.CategoriaRequest
import br.dev.s2w.alura.flix.adapter.controller.response.CategoriaResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.util.UriComponentsBuilder

interface CategoriaAPI {
    fun findAllCategorias(): List<CategoriaResponse>

    fun findCategoriaById(categoriaId: Long): ResponseEntity<CategoriaResponse>

    fun insertCategoria(categoriaRequest: CategoriaRequest, uriBuilder: UriComponentsBuilder): ResponseEntity<CategoriaResponse>

    fun updateCategoria(categoriaId: Long, categoriaRequest: CategoriaRequest): ResponseEntity<CategoriaResponse>

    fun deleteCategoriaById(categoriaId: Long): ResponseEntity<Unit>
}