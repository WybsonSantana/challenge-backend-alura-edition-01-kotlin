package br.dev.s2w.alura.flix.gateway.repository.mapper

import br.dev.s2w.alura.flix.domain.model.Categoria
import br.dev.s2w.alura.flix.gateway.repository.entity.CategoriaEntity

object CategoriaEntityMapper {

    fun Categoria.toCategoriaEntity(): CategoriaEntity {
        return CategoriaEntity(id, titulo, cor)
    }

    fun CategoriaEntity.toCategoria(): Categoria {
        return Categoria(id, titulo, cor)
    }
}