package br.dev.s2w.alura.flix.gateway

import br.dev.s2w.alura.flix.domain.gateway.FindAllVideos
import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.gateway.repository.VideoRepository
import br.dev.s2w.alura.flix.gateway.repository.mapper.VideoEntityMapper.toVideo
import org.springframework.stereotype.Component

@Component
class FindAllVideosImpl(
    private val videoRepository: VideoRepository
) : FindAllVideos {

    override fun fetch(): List<Video> {
        return videoRepository.findAll().map { it.toVideo() }
    }
}