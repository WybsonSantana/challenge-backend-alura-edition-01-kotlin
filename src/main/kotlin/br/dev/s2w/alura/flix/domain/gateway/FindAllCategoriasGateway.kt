package br.dev.s2w.alura.flix.domain.gateway

import br.dev.s2w.alura.flix.domain.model.Categoria

interface FindAllCategoriasGateway {
    fun fetch(): List<Categoria>
}