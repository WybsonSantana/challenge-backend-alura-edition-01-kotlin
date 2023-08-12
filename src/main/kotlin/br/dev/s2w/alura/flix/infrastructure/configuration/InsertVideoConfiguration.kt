package br.dev.s2w.alura.flix.infrastructure.configuration

import br.dev.s2w.alura.flix.domain.service.VideoService
import br.dev.s2w.alura.flix.domain.usecase.InsertVideoUsecase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InsertVideoConfiguration {

    @Bean
    fun insertVideo(videoService: VideoService): InsertVideoUsecase {
        return InsertVideoUsecase(videoService)
    }
}