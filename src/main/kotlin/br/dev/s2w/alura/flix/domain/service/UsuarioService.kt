package br.dev.s2w.alura.flix.domain.service

import br.dev.s2w.alura.flix.domain.model.Usuario

interface UsuarioService {
    fun retrieveOneBy(username: String): Usuario
}