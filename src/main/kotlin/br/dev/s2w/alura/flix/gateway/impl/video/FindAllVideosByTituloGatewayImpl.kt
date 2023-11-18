package br.dev.s2w.alura.flix.gateway.impl.video

import br.dev.s2w.alura.flix.domain.gateway.video.FindAllVideosByTituloGateway
import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.gateway.repository.VideoRepository
import br.dev.s2w.alura.flix.gateway.repository.mapper.VideoEntityMapper.toVideo
import org.springframework.stereotype.Component

@Component
class FindAllVideosByTituloGatewayImpl(
    private val videoRepository: VideoRepository
) : FindAllVideosByTituloGateway {

    override fun fetchByTitulo(titulo: String): List<Video> {
        return videoRepository.findByTituloContaining(titulo).map { it.toVideo() }
    }
}