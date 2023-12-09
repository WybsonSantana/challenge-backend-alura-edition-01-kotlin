package br.dev.s2w.alura.flix.gateway.repository.entity

import javax.persistence.*

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