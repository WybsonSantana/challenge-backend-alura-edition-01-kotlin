package br.dev.s2w.alura.flix.adapter.controller

import br.dev.s2w.alura.flix.adapter.api.CategoriaAPI
import br.dev.s2w.alura.flix.adapter.controller.mapper.CategoriaMapper.toCategoriaResponse
import br.dev.s2w.alura.flix.adapter.controller.response.CategoriaResponse
import br.dev.s2w.alura.flix.domain.usecase.FindAllCategoriasUsecase
import br.dev.s2w.alura.flix.infrastructure.utility.Constants
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.CATEGORIA_API_V1_MAPPING
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestController
@RequestMapping(CATEGORIA_API_V1_MAPPING)
class CategoriaController(
    private val findAllCategoriasUsecase: FindAllCategoriasUsecase
) : CategoriaAPI {

    @GetMapping
    override fun findAllCategorias(): List<CategoriaResponse> {
        return findAllCategoriasUsecase.execute().map { it.toCategoriaResponse() }
    }
}