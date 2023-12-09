package br.dev.s2w.alura.flix.adapter.controller.mapper

import br.dev.s2w.alura.flix.adapter.controller.request.CategoriaRequest
import br.dev.s2w.alura.flix.adapter.controller.response.CategoriaResponse
import br.dev.s2w.alura.flix.domain.model.Categoria

object CategoriaMapper {

    fun Categoria.toCategoriaResponse(): CategoriaResponse {
        return CategoriaResponse(id!!, titulo, cor)
    }

    fun CategoriaRequest.toCategoria(): Categoria {
        return Categoria(titulo = titulo, cor = cor)
    }
}