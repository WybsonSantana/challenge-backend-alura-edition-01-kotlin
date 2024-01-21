package br.dev.s2w.alura.flix.adapter.security.mapper

import br.dev.s2w.alura.flix.adapter.security.dto.UsuarioDTO
import br.dev.s2w.alura.flix.domain.model.Usuario

object UsuarioMapper {

    fun Usuario.toUsuarioDTO(): UsuarioDTO {
        return UsuarioDTO(email, password)
    }

}