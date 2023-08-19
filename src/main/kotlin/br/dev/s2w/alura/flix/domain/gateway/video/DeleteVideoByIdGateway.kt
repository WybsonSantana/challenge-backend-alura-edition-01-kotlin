package br.dev.s2w.alura.flix.domain.gateway.video

interface DeleteVideoByIdGateway {
    fun removeOneBy(id: Long)

}