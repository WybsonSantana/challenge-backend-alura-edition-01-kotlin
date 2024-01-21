package br.dev.s2w.alura.flix.gateway.repository

import br.dev.s2w.alura.flix.gateway.repository.entity.UsuarioEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsuarioRepository : JpaRepository<UsuarioEntity, Long> {
    fun findByEmail(username: String): UsuarioEntity
}