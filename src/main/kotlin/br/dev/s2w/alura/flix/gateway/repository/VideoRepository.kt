package br.dev.s2w.alura.flix.gateway.repository

import br.dev.s2w.alura.flix.gateway.repository.entity.VideoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface VideoRepository : JpaRepository<VideoEntity, Long> {
    fun findByCategoriaId(categoriaId: Long): List<VideoEntity>

    fun findByTituloContaining(titulo: String): List<VideoEntity>
}