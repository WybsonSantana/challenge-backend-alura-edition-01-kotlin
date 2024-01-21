package br.dev.s2w.alura.flix.domain.service.impl

import br.dev.s2w.alura.flix.domain.gateway.usuario.FindUsuarioByEmailGateway
import br.dev.s2w.alura.flix.domain.model.Usuario
import br.dev.s2w.alura.flix.domain.service.UsuarioService

class UsuarioServiceImpl(
    private val findUsuarioByEmailGateway: FindUsuarioByEmailGateway
) : UsuarioService {

    override fun retrieveOneBy(username: String): Usuario {
        return findUsuarioByEmailGateway.fetchOneBy(username)
    }
}