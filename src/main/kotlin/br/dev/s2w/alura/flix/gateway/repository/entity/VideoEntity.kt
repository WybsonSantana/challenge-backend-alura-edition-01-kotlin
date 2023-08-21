package br.dev.s2w.alura.flix.gateway.repository.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "video")
data class VideoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val titulo: String,

    val descricao: String,

    val url: String,

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    val categoria: CategoriaEntity? = null
)