package br.dev.s2w.alura.flix.domain.usecase

import br.dev.s2w.alura.flix.domain.model.Categoria
import br.dev.s2w.alura.flix.domain.service.CategoriaService

class FindAllCategoriasUsecase(
    private val categoriaService: CategoriaService
) {

    fun execute(): List<Categoria> {
        return categoriaService.retrieve()
    }
}