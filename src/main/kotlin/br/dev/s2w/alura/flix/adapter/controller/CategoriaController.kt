package br.dev.s2w.alura.flix.adapter.controller

import br.dev.s2w.alura.flix.adapter.api.CategoriaAPI
import br.dev.s2w.alura.flix.adapter.controller.mapper.CategoriaMapper.toCategoriaResponse
import br.dev.s2w.alura.flix.adapter.controller.response.CategoriaResponse
import br.dev.s2w.alura.flix.domain.usecase.categoria.FindAllCategoriasUsecase
import br.dev.s2w.alura.flix.domain.usecase.categoria.FindCategoriaByIdUsecase
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.CATEGORIA_API_V1_MAPPING
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(CATEGORIA_API_V1_MAPPING)
class CategoriaController(
    private val findAllCategoriasUsecase: FindAllCategoriasUsecase,
    private val findCategoriaByIdUsecase: FindCategoriaByIdUsecase
) : CategoriaAPI {

    @GetMapping
    override fun findAllCategorias(): List<CategoriaResponse> {
        return findAllCategoriasUsecase.execute().map { it.toCategoriaResponse() }
    }

    @GetMapping("/{id}")
    override fun findCategoriaById(@PathVariable id: Long): ResponseEntity<CategoriaResponse> {
        val categoriaResponse = findCategoriaByIdUsecase.execute(id).toCategoriaResponse()
        return ResponseEntity.ok(categoriaResponse)
    }
}