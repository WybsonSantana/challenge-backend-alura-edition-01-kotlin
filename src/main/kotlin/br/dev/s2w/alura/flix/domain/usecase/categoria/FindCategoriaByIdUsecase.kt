package br.dev.s2w.alura.flix.domain.usecase.categoria

import br.dev.s2w.alura.flix.domain.model.Categoria
import br.dev.s2w.alura.flix.domain.service.CategoriaService

class FindCategoriaByIdUsecase(
    private val categoriaService: CategoriaService
) {

    fun execute(id: Long): Categoria {
        return categoriaService.retrieveOneBy(id)
    }
}