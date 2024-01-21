package br.dev.s2w.alura.flix.infrastructure.configuration.usuario

import br.dev.s2w.alura.flix.domain.service.UsuarioService
import br.dev.s2w.alura.flix.domain.usecase.usuario.FindUsuarioByEmailUsecase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FindUsuarioByEmailConfiguration {

    @Bean
    fun findUsuarioByEmailUsecase(usuarioService: UsuarioService): FindUsuarioByEmailUsecase {
        return FindUsuarioByEmailUsecase(usuarioService)

    }
}