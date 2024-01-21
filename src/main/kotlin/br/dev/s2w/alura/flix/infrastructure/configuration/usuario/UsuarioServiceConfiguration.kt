package br.dev.s2w.alura.flix.infrastructure.configuration.usuario

import br.dev.s2w.alura.flix.domain.gateway.usuario.FindUsuarioByEmailGateway
import br.dev.s2w.alura.flix.domain.service.UsuarioService
import br.dev.s2w.alura.flix.domain.service.impl.UsuarioServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UsuarioServiceConfiguration {

    @Bean
    fun usuarioService(findUsuarioByEmailGateway: FindUsuarioByEmailGateway): UsuarioService {
        return UsuarioServiceImpl(findUsuarioByEmailGateway)
    }
}