package br.dev.s2w.alura.flix.domain.service

import br.dev.s2w.alura.flix.domain.model.Video

interface VideoService {
    fun retrieve(): List<Video>
    fun retriveOne(id: Long): Video
    fun saveOne(video: Video): Video
    fun modifyOne(id: Long, video: Video): Video
}