package br.dev.s2w.alura.flix.gateway.repository.entity

import javax.persistence.*

@Entity
@Table(name = "categoria")
data class CategoriaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val titulo: String,

    val cor: String
)