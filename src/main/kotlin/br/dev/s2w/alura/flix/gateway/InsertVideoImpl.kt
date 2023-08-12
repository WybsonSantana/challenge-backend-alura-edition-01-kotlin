package br.dev.s2w.alura.flix.gateway

import br.dev.s2w.alura.flix.domain.gateway.InsertVideo
import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.gateway.repository.VideoRepository
import br.dev.s2w.alura.flix.gateway.repository.mapper.VideoEntityMapper.toVideo
import br.dev.s2w.alura.flix.gateway.repository.mapper.VideoEntityMapper.toVideoEntity
import org.springframework.stereotype.Component

@Component
class InsertVideoImpl(
    private val videoRepository: VideoRepository
) : InsertVideo {

    override fun saveOne(video: Video): Video {
        videoRepository.save(video.toVideoEntity()).also {
            return it.toVideo()
        }
    }
}