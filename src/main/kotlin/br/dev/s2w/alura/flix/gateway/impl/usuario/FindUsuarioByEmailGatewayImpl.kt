package br.dev.s2w.alura.flix.gateway.impl.usuario

import br.dev.s2w.alura.flix.domain.gateway.usuario.FindUsuarioByEmailGateway
import br.dev.s2w.alura.flix.domain.model.Usuario
import br.dev.s2w.alura.flix.gateway.repository.UsuarioRepository
import br.dev.s2w.alura.flix.gateway.repository.mapper.UsuarioEntityMapper.toUsuario
import org.springframework.stereotype.Component

@Component
class FindUsuarioByEmailGatewayImpl(
    private val usuarioRepository: UsuarioRepository
): FindUsuarioByEmailGateway {

    override fun fetchOneBy(username: String): Usuario {
        return usuarioRepository.findByEmail(username).toUsuario()
    }
}