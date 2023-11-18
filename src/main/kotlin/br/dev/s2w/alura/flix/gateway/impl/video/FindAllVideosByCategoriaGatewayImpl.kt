package br.dev.s2w.alura.flix.gateway.impl.video

import br.dev.s2w.alura.flix.domain.gateway.video.FindAllVideosByCategoriaGateway
import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.gateway.repository.VideoRepository
import br.dev.s2w.alura.flix.gateway.repository.mapper.VideoEntityMapper.toVideo
import org.springframework.stereotype.Component

@Component
class FindAllVideosByCategoriaGatewayImpl(
    private val videoRepository: VideoRepository
) : FindAllVideosByCategoriaGateway {

    override fun fetchByCategoria(id: Long): List<Video> {
        return videoRepository.findByCategoriaId(id).map { it.toVideo() }
    }
}