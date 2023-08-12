package br.dev.s2w.alura.flix.domain.gateway

interface DeleteVideoById {
    fun removeOne(id: Long)

}