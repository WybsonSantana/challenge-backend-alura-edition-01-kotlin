package br.dev.s2w.alura.flix.adapter.security.authentication

import br.dev.s2w.alura.flix.adapter.security.dto.UsuarioDTO
import br.dev.s2w.alura.flix.domain.model.Usuario
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsAuthentication(
    private val usuarioDTO: UsuarioDTO
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()

    override fun getUsername(): String = usuarioDTO.username

    override fun getPassword(): String = usuarioDTO.password

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}