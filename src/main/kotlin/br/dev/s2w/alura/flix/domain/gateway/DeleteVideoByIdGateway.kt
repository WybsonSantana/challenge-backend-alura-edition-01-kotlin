package br.dev.s2w.alura.flix.domain.gateway

interface DeleteVideoByIdGateway {
    fun removeOneBy(id: Long)

}