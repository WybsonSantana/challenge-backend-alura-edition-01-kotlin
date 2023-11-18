package br.dev.s2w.alura.flix.infrastructure.configuration.video

import br.dev.s2w.alura.flix.domain.service.VideoService
import br.dev.s2w.alura.flix.domain.usecase.video.FindAllVideosByCategoriaUsecase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FindAllVideosByCategoriaConfiguration {

    @Bean
    fun findAllVideosByCategoriaUsecase(videoService: VideoService): FindAllVideosByCategoriaUsecase {
        return FindAllVideosByCategoriaUsecase(videoService)
    }
}