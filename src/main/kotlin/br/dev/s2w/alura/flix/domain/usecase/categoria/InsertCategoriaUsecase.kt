package br.dev.s2w.alura.flix.domain.usecase.categoria

import br.dev.s2w.alura.flix.domain.model.Categoria
import br.dev.s2w.alura.flix.domain.service.CategoriaService

class InsertCategoriaUsecase(
    private val categoriaService: CategoriaService
) {

    fun execute(categoria: Categoria): Categoria {
        return categoriaService.saveOne(categoria)
    }
}