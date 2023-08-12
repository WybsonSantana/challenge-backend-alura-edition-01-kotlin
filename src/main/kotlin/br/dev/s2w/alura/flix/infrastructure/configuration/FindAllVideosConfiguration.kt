package br.dev.s2w.alura.flix.infrastructure.configuration

import br.dev.s2w.alura.flix.domain.gateway.FindAllVideos
import br.dev.s2w.alura.flix.domain.gateway.FindVideoById
import br.dev.s2w.alura.flix.domain.gateway.InsertVideo
import br.dev.s2w.alura.flix.domain.service.VideoService
import br.dev.s2w.alura.flix.domain.service.impl.VideoServiceImpl
import br.dev.s2w.alura.flix.domain.usecase.FindAllVideosUsecase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FindAllVideosConfiguration {

    @Bean
    fun findAllVideosService(findAllVideos: FindAllVideos, findVideoById: FindVideoById, insertVideo: InsertVideo): VideoService {
        return VideoServiceImpl(findAllVideos, findVideoById, insertVideo)
    }

    @Bean
    fun findAllVideosUsecase(videoService: VideoService): FindAllVideosUsecase {
        return FindAllVideosUsecase(videoService)
    }
}