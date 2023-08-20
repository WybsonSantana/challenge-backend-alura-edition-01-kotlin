package br.dev.s2w.alura.flix.domain.gateway.categoria

interface DeleteCategoriaByIdGateway {
    fun removeOneBy(id: Long)
}