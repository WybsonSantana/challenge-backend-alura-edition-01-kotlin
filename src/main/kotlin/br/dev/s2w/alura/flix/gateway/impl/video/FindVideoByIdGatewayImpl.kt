package br.dev.s2w.alura.flix.gateway.impl.video

import br.dev.s2w.alura.flix.domain.exception.VideoNotFoundException
import br.dev.s2w.alura.flix.domain.gateway.video.FindVideoByIdGateway
import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.gateway.repository.VideoRepository
import br.dev.s2w.alura.flix.gateway.repository.mapper.VideoEntityMapper.toVideo
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.VIDEO_NOT_FOUND_EXCEPTION_MESSAGE
import org.springframework.stereotype.Component

@Component
class FindVideoByIdGatewayImpl(
    private val videoRepository: VideoRepository
) : FindVideoByIdGateway {

    override fun fetchOneBy(id: Long): Video {
        try {
            return videoRepository.findById(id).get().toVideo()
        } catch (e: NoSuchElementException) {
            throw VideoNotFoundException(VIDEO_NOT_FOUND_EXCEPTION_MESSAGE)
        }
    }
}