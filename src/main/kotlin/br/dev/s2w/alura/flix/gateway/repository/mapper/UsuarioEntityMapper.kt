package br.dev.s2w.alura.flix.gateway.repository.mapper

import br.dev.s2w.alura.flix.domain.model.Usuario
import br.dev.s2w.alura.flix.gateway.repository.entity.UsuarioEntity

object UsuarioEntityMapper {

    fun UsuarioEntity.toUsuario(): Usuario {
        return Usuario(id, nome, email, password)
    }
}