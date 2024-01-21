package br.dev.s2w.alura.flix.domain.usecase.usuario

import br.dev.s2w.alura.flix.domain.model.Usuario
import br.dev.s2w.alura.flix.domain.service.UsuarioService

class FindUsuarioByEmailUsecase(
    private val usuarioService: UsuarioService
) {

    fun execute(username: String): Usuario {
        return usuarioService.retrieveOneBy(username)
    }
}