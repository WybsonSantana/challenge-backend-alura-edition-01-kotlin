package br.dev.s2w.alura.flix.infrastructure.configuration.video

import br.dev.s2w.alura.flix.domain.gateway.video.*
import br.dev.s2w.alura.flix.domain.service.VideoService
import br.dev.s2w.alura.flix.domain.service.impl.VideoServiceImpl
import br.dev.s2w.alura.flix.domain.usecase.video.FindAllVideosByTituloUsecase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class VideoServiceConfiguration {

    @Bean
    fun videoService(
        findAllVideosGateway: FindAllVideosGateway,
        findAllVideosByCategoriaGateway: FindAllVideosByCategoriaGateway,
        findAllVideosByTituloGateway: FindAllVideosByTituloGateway,
        findVideoByIdGateway: FindVideoByIdGateway,
        insertVideoGateway: InsertVideoGateway,
        updateVideoByIdGateway: UpdateVideoByIdGateway,
        deleteVideoByIdGateway: DeleteVideoByIdGateway
    ): VideoService {
        return VideoServiceImpl(
            findAllVideosGateway,
            findAllVideosByCategoriaGateway,
            findAllVideosByTituloGateway,
            findVideoByIdGateway,
            insertVideoGateway,
            updateVideoByIdGateway,
            deleteVideoByIdGateway
        )
    }
}