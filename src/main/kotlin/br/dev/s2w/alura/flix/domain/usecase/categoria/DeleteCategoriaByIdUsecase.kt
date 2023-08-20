package br.dev.s2w.alura.flix.domain.usecase.categoria

import br.dev.s2w.alura.flix.domain.service.CategoriaService

class DeleteCategoriaByIdUsecase(
    private val categoriaService: CategoriaService
) {

    fun execute(id: Long) {
        categoriaService.removeOneBy(id)
    }
}