package br.dev.s2w.alura.flix.infrastructure.configuration.security

import br.dev.s2w.alura.flix.infrastructure.utility.Constants.CATEGORIA_V1_API_PATH
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.SWAGGER_UI
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.SWAGGER_UI_INDEX
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.V3_API_DOCS
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.VIDEO_V1_API_PATH
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfiguration {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf().disable()
            .authorizeHttpRequests()
            .antMatchers(CATEGORIA_V1_API_PATH.plus("/**")).authenticated()
            .antMatchers(VIDEO_V1_API_PATH.plus("/**")).authenticated()
            .antMatchers(SWAGGER_UI, SWAGGER_UI_INDEX).permitAll()
            .antMatchers(V3_API_DOCS).permitAll()
            .anyRequest().denyAll()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().formLogin().disable()
            .httpBasic()

        return http.build()
    }

    @Bean
    fun decoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}