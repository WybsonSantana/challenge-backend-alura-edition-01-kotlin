package br.dev.s2w.alura.flix.gateway.impl

import br.dev.s2w.alura.flix.domain.gateway.InsertVideoGateway
import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.gateway.repository.VideoRepository
import br.dev.s2w.alura.flix.gateway.repository.mapper.VideoEntityMapper.toVideo
import br.dev.s2w.alura.flix.gateway.repository.mapper.VideoEntityMapper.toVideoEntity
import org.springframework.stereotype.Component

@Component
class InsertVideoGatewayImpl(
    private val videoRepository: VideoRepository
) : InsertVideoGateway {

    override fun saveOne(video: Video): Video {
        videoRepository.save(video.toVideoEntity()).also {
            return it.toVideo()
        }
    }
}