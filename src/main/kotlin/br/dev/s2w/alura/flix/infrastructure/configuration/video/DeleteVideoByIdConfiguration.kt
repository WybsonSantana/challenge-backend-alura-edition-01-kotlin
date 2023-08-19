package br.dev.s2w.alura.flix.infrastructure.configuration.video

import br.dev.s2w.alura.flix.domain.service.VideoService
import br.dev.s2w.alura.flix.domain.usecase.DeleteVideoByIdUsecase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DeleteVideoByIdConfiguration {

    @Bean
    fun deleteVideoByIdUsecase(videoService: VideoService): DeleteVideoByIdUsecase {
        return DeleteVideoByIdUsecase(videoService)
    }
}