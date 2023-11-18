package br.dev.s2w.alura.flix.gateway.repository

import br.dev.s2w.alura.flix.gateway.repository.entity.CategoriaEntity
import br.dev.s2w.alura.flix.gateway.repository.entity.VideoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoriaRepository : JpaRepository<CategoriaEntity, Long>