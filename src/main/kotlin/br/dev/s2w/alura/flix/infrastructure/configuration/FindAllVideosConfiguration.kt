package br.dev.s2w.alura.flix.infrastructure.configuration

import br.dev.s2w.alura.flix.domain.gateway.FindAllVideos
import br.dev.s2w.alura.flix.domain.service.VideoService
import br.dev.s2w.alura.flix.domain.service.impl.VideoServiceImpl
import br.dev.s2w.alura.flix.domain.usecase.FindAllVideosUsecase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FindAllVideosConfiguration {

    @Bean
    fun findAllVideosService(findAllVideos: FindAllVideos): VideoService {
        return VideoServiceImpl(findAllVideos)
    }

    @Bean
    fun findAllVideosUsecase(videoService: VideoService): FindAllVideosUsecase {
        return FindAllVideosUsecase(videoService)
    }
}