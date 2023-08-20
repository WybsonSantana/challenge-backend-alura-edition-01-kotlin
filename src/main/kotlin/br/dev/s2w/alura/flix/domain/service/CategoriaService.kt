package br.dev.s2w.alura.flix.domain.service

import br.dev.s2w.alura.flix.domain.model.Categoria

interface CategoriaService {
    fun retrieve(): List<Categoria>

    fun retrieveOneBy(id: Long): Categoria

    fun saveOne(categoria: Categoria): Categoria

    fun modifyOneBy(id: Long, categoria: Categoria): Categoria

    fun removeOneBy(id: Long)
}