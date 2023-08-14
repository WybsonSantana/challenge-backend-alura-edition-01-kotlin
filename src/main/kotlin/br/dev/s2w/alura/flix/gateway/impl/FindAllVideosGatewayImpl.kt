package br.dev.s2w.alura.flix.gateway.impl

import br.dev.s2w.alura.flix.domain.gateway.FindAllVideosGateway
import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.gateway.repository.VideoRepository
import br.dev.s2w.alura.flix.gateway.repository.mapper.VideoEntityMapper.toVideo
import org.springframework.stereotype.Component

@Component
class FindAllVideosGatewayImpl(
    private val videoRepository: VideoRepository
) : FindAllVideosGateway {

    override fun fetch(): List<Video> {
        return videoRepository.findAll().map { it.toVideo() }
    }
}