package br.dev.s2w.alura.flix.domain.usecase.categoria

import br.dev.s2w.alura.flix.domain.model.Categoria
import br.dev.s2w.alura.flix.domain.service.CategoriaService

class UpdateCategoriaByIdUsecase(
    private val categoriaService: CategoriaService
) {

    fun execute(id: Long, categoria: Categoria): Categoria {
        return categoriaService.modifyOneBy(id, categoria)
    }
}