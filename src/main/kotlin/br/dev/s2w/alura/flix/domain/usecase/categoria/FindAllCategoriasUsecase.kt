package br.dev.s2w.alura.flix.domain.usecase.categoria

import br.dev.s2w.alura.flix.domain.model.Categoria
import br.dev.s2w.alura.flix.domain.service.CategoriaService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

class FindAllCategoriasUsecase(
    private val categoriaService: CategoriaService
) {

    fun execute(pageable: Pageable): Page<Categoria> {
        return categoriaService.retrieve(pageable)
    }
}