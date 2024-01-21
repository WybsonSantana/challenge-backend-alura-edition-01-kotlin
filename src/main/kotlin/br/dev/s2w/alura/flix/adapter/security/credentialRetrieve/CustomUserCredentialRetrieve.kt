package br.dev.s2w.alura.flix.adapter.security.credentialRetrieve

import br.dev.s2w.alura.flix.adapter.security.authentication.UserDetailsAuthentication
import br.dev.s2w.alura.flix.adapter.security.mapper.UsuarioMapper.toUsuarioDTO
import br.dev.s2w.alura.flix.domain.usecase.usuario.FindUsuarioByEmailUsecase
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomUserCredentialRetrieve(
    private val findUsuarioByEmailUsecase: FindUsuarioByEmailUsecase
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val usuarioDTO = findUsuarioByEmailUsecase.execute(username).toUsuarioDTO()
        return UserDetailsAuthentication(usuarioDTO)
    }
}