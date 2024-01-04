package br.dev.s2w.alura.flix.gateway.impl.video

import br.dev.s2w.alura.flix.domain.gateway.video.FindAllVideosGateway
import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.gateway.repository.VideoRepository
import br.dev.s2w.alura.flix.gateway.repository.mapper.VideoEntityMapper.toVideo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class FindAllVideosGatewayImpl(
    private val videoRepository: VideoRepository
) : FindAllVideosGateway {

    override fun fetch(pageable: Pageable): Page<Video> {
        return videoRepository.findAll(pageable).map { it.toVideo() }
    }
}