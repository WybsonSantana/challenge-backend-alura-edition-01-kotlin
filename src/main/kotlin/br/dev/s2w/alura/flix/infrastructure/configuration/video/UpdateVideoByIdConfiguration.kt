package br.dev.s2w.alura.flix.infrastructure.configuration.video

import br.dev.s2w.alura.flix.domain.service.CategoriaService
import br.dev.s2w.alura.flix.domain.service.VideoService
import br.dev.s2w.alura.flix.domain.usecase.video.UpdateVideoByIdUsecase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UpdateVideoByIdConfiguration {

    @Bean
    fun updateVideoUsecase(videoService: VideoService, categoriaService: CategoriaService): UpdateVideoByIdUsecase {
        return UpdateVideoByIdUsecase(videoService, categoriaService)
    }
}