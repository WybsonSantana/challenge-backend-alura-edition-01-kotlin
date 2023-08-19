package br.dev.s2w.alura.flix.infrastructure.configuration.video

import br.dev.s2w.alura.flix.domain.service.VideoService
import br.dev.s2w.alura.flix.domain.usecase.FindVideoByIdUsecase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FindVideoByIdConfiguration {

    @Bean
    fun findVideoByIdUsecase(videoService: VideoService): FindVideoByIdUsecase {
        return FindVideoByIdUsecase(videoService)
    }
}