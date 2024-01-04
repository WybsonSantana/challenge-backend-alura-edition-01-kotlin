package br.dev.s2w.alura.flix.domain.service

import br.dev.s2w.alura.flix.domain.model.Categoria
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CategoriaService {
    fun retrieve(pageable: Pageable): Page<Categoria>

    fun retrieveOneBy(id: Long): Categoria

    fun saveOne(categoria: Categoria): Categoria

    fun modifyOneBy(id: Long, categoria: Categoria): Categoria

    fun removeOneBy(id: Long)
}