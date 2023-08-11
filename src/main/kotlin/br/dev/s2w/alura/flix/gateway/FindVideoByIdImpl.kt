package br.dev.s2w.alura.flix.gateway

import br.dev.s2w.alura.flix.domain.gateway.FindVideoById
import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.gateway.repository.VideoRepository
import br.dev.s2w.alura.flix.gateway.repository.mapper.VideoEntityMapper.toVideo
import org.springframework.stereotype.Component

@Component
class FindVideoByIdImpl(
    private val videoRepository: VideoRepository
) : FindVideoById {

    override fun fetchOne(id: Long): Video {
        return videoRepository.findById(id).get().toVideo()
    }
}