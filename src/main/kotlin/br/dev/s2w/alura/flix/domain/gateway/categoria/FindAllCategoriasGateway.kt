package br.dev.s2w.alura.flix.domain.gateway.categoria

import br.dev.s2w.alura.flix.domain.model.Categoria
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FindAllCategoriasGateway {
    fun fetch(pageable: Pageable): Page<Categoria>
}