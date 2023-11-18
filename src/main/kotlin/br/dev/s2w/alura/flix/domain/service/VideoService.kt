package br.dev.s2w.alura.flix.domain.service

import br.dev.s2w.alura.flix.domain.model.Video

interface VideoService {
    fun retrieve(): List<Video>

    fun retriveAllByCategoria(id: Long): List<Video>

    fun retriveAllByTitulo(titulo: String): List<Video>

    fun retriveOneBy(id: Long): Video

    fun saveOne(video: Video): Video

    fun modifyOneBy(id: Long, video: Video): Video

    fun removeOneBy(id: Long)
}