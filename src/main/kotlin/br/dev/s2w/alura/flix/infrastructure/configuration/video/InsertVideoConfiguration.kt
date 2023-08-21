package br.dev.s2w.alura.flix.infrastructure.configuration.video

import br.dev.s2w.alura.flix.domain.service.CategoriaService
import br.dev.s2w.alura.flix.domain.service.VideoService
import br.dev.s2w.alura.flix.domain.usecase.video.InsertVideoUsecase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InsertVideoConfiguration {

    @Bean
    fun insertVideoUsecase(videoService: VideoService, categoriaService: CategoriaService): InsertVideoUsecase {
        return InsertVideoUsecase(videoService, categoriaService)
    }
}