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
        findAllVideosGateway: FindAllVideosGateway,
        findVideoByIdGateway: FindVideoByIdGateway,
        insertVideoGateway: InsertVideoGateway,
        updateVideoByIdGateway: UpdateVideoByIdGateway,
        deleteVideoByIdGateway: DeleteVideoByIdGateway
    ): VideoService {
        return VideoServiceImpl(
            findAllVideosGateway,
            findVideoByIdGateway,
            insertVideoGateway,
            updateVideoByIdGateway,
            deleteVideoByIdGateway
        )
    }
}