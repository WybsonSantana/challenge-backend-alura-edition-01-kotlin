package br.dev.s2w.alura.flix.infrastructure.configuration.video

import br.dev.s2w.alura.flix.domain.service.VideoService
import br.dev.s2w.alura.flix.domain.usecase.video.FindAllVideosByTituloUsecase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FindAllVideosByTituloConfiguration {

    @Bean
    fun findAllVideosByTituloUsecase(videoService: VideoService): FindAllVideosByTituloUsecase {
        return FindAllVideosByTituloUsecase(videoService)
    }

}