package br.dev.s2w.alura.flix.infrastructure.configuration

import br.dev.s2w.alura.flix.domain.gateway.*
import br.dev.s2w.alura.flix.domain.service.VideoService
import br.dev.s2w.alura.flix.domain.service.impl.VideoServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class VideoServiceConfiguration {

    @Bean
    fun videoService(
        findAllVideos: FindAllVideos,
        findVideoById: FindVideoById,
        insertVideo: InsertVideo,
        updateVideo: UpdateVideo,
        deleteVideoById: DeleteVideoById
    ): VideoService {
        return VideoServiceImpl(findAllVideos, findVideoById, insertVideo, updateVideo, deleteVideoById)
    }
}