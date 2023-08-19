package br.dev.s2w.alura.flix.infrastructure.configuration.video

import br.dev.s2w.alura.flix.domain.service.VideoService
import br.dev.s2w.alura.flix.domain.usecase.video.UpdateVideoUsecase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UpdateVideoConfiguration {

    @Bean
    fun updateVideo(videoService: VideoService): UpdateVideoUsecase {
        return UpdateVideoUsecase(videoService)
    }
}