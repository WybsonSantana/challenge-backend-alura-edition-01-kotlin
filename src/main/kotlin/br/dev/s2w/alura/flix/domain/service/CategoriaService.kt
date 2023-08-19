package br.dev.s2w.alura.flix.domain.service

import br.dev.s2w.alura.flix.domain.model.Categoria

interface CategoriaService {
    fun retrieve(): List<Categoria>
}