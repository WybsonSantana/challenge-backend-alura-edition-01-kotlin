package br.dev.s2w.alura.flix.adapter.controller

import br.dev.s2w.alura.flix.adapter.api.CategoriaAPI
import br.dev.s2w.alura.flix.adapter.controller.mapper.CategoriaMapper.toCategoria
import br.dev.s2w.alura.flix.adapter.controller.mapper.CategoriaMapper.toCategoriaResponse
import br.dev.s2w.alura.flix.adapter.controller.request.CategoriaRequest
import br.dev.s2w.alura.flix.adapter.controller.response.CategoriaResponse
import br.dev.s2w.alura.flix.domain.model.Categoria
import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.domain.usecase.categoria.FindAllCategoriasUsecase
import br.dev.s2w.alura.flix.domain.usecase.categoria.FindCategoriaByIdUsecase
import br.dev.s2w.alura.flix.domain.usecase.categoria.InsertCategoriaUsecase
import br.dev.s2w.alura.flix.domain.usecase.categoria.UpdateCategoriaUsecase
import br.dev.s2w.alura.flix.infrastructure.utility.Constants
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.CATEGORIA_API_V1_MAPPING
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping(CATEGORIA_API_V1_MAPPING)
class CategoriaController(
    private val findAllCategoriasUsecase: FindAllCategoriasUsecase,
    private val findCategoriaByIdUsecase: FindCategoriaByIdUsecase,
    private val insertCategoriaUsecase: InsertCategoriaUsecase,
    private val updateCategoriaUsecase: UpdateCategoriaUsecase
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

    @PostMapping
    override fun insertCategoria(
        @RequestBody @Valid categoriaRequest: CategoriaRequest,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<CategoriaResponse> {
        insertCategoriaUsecase.execute(categoriaRequest.toCategoria()).also {
            val uri = buildCategoriaUri(it, uriBuilder)
            return ResponseEntity.created(uri).body(it.toCategoriaResponse())
        }
    }

    @PutMapping("/{id}")
    override fun updateCategoria(
        @PathVariable id: Long,
        @RequestBody @Valid categoriaRequest: CategoriaRequest
    ): ResponseEntity<CategoriaResponse> {
        updateCategoriaUsecase.execute(id, categoriaRequest.toCategoria()).also {
            return ResponseEntity.ok(it.toCategoriaResponse())
        }
    }

    private fun buildCategoriaUri(createdCategoria: Categoria, uriBuilder: UriComponentsBuilder): URI {
        return uriBuilder.path("${Constants.CATEGORIA_API_V1_MAPPING}/${createdCategoria.id}").build().toUri()
    }
}