package br.dev.s2w.alura.flix.adapter.controller

import br.dev.s2w.alura.flix.adapter.api.CategoriaAPI
import br.dev.s2w.alura.flix.adapter.controller.mapper.CategoriaMapper.toCategoria
import br.dev.s2w.alura.flix.adapter.controller.mapper.CategoriaMapper.toCategoriaResponse
import br.dev.s2w.alura.flix.adapter.controller.request.CategoriaRequest
import br.dev.s2w.alura.flix.adapter.controller.response.CategoriaResponse
import br.dev.s2w.alura.flix.domain.model.Categoria
import br.dev.s2w.alura.flix.domain.usecase.categoria.*
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.CATEGORIA_V1_API_PATH
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
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
@RequestMapping(CATEGORIA_V1_API_PATH)
class CategoriaController(
    private val findAllCategoriasUsecase: FindAllCategoriasUsecase,
    private val findCategoriaByIdUsecase: FindCategoriaByIdUsecase,
    private val insertCategoriaUsecase: InsertCategoriaUsecase,
    private val updateCategoriaByIdUsecase: UpdateCategoriaByIdUsecase,
    private val deleteCategoriaByIdUsecase: DeleteCategoriaByIdUsecase
) : CategoriaAPI {

    @GetMapping
    override fun findAllCategorias(): List<CategoriaResponse> {
        return findAllCategoriasUsecase.execute().map { it.toCategoriaResponse() }
    }

    @GetMapping("/{categoriaId}")
    override fun findCategoriaById(@PathVariable categoriaId: Long): ResponseEntity<CategoriaResponse> {
        val categoriaResponse = findCategoriaByIdUsecase.execute(categoriaId).toCategoriaResponse()

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

    @PutMapping("/{categoriaId}")
    override fun updateCategoria(
        @PathVariable categoriaId: Long,
        @RequestBody @Valid categoriaRequest: CategoriaRequest
    ): ResponseEntity<CategoriaResponse> {
        updateCategoriaByIdUsecase.execute(categoriaId, categoriaRequest.toCategoria()).also {
            return ResponseEntity.ok(it.toCategoriaResponse())
        }
    }

    @DeleteMapping("/{categoriaId}")
    override fun deleteCategoriaById(@PathVariable categoriaId: Long): ResponseEntity<Unit> {
        deleteCategoriaByIdUsecase.execute(categoriaId).also {
            return ResponseEntity.noContent().build()
        }
    }

    private fun buildCategoriaUri(createdCategoria: Categoria, uriBuilder: UriComponentsBuilder): URI {
        val categoriaId = createdCategoria.id
        val categoriaApiPath = CATEGORIA_V1_API_PATH
        val categoriaUri = uriBuilder
            .path("/$categoriaApiPath/$categoriaId")
            .build()
            .toUri()

        return categoriaUri
    }
}