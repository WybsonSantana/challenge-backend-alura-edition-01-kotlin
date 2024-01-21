package br.dev.s2w.alura.flix.domain.gateway.usuario

import br.dev.s2w.alura.flix.domain.model.Usuario

interface FindUsuarioByEmailGateway {
    fun fetchOneBy(username: String): Usuario
}