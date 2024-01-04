package br.dev.s2w.alura.flix.domain.service

import br.dev.s2w.alura.flix.domain.model.Video
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface VideoService {
    fun retrieve(pageable: Pageable): Page<Video>

    fun retriveAllByCategoria(id: Long, pageable: Pageable): Page<Video>

    fun retriveAllByTitulo(titulo: String, pageable: Pageable): Page<Video>

    fun retriveOneBy(id: Long): Video

    fun saveOne(video: Video): Video

    fun modifyOneBy(id: Long, video: Video): Video

    fun removeOneBy(id: Long)
}