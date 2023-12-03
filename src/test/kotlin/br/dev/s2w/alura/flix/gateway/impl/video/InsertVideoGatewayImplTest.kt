package br.dev.s2w.alura.flix.gateway.impl.video

import br.dev.s2w.alura.flix.domain.gateway.video.InsertVideoGateway
import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.gateway.repository.VideoRepository
import br.dev.s2w.alura.flix.gateway.repository.entity.VideoEntity
import br.dev.s2w.alura.flix.gateway.repository.mapper.VideoEntityMapper.toVideo
import br.dev.s2w.alura.flix.gateway.repository.mapper.VideoEntityMapper.toVideoEntity
import br.dev.s2w.alura.flix.utility.GeneralBeans
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class InsertVideoGatewayImplTest : GeneralBeans() {

    @Mock
    private lateinit var videoRepository: VideoRepository

    private lateinit var insertVideoGateway: InsertVideoGateway

    @BeforeEach
    fun setup() {
        insertVideoGateway = InsertVideoGatewayImpl(videoRepository)
    }

    @Test
    fun `should save video ID 04`() {
        val fileQueryResultUri = super.getVideoId04QueryResultUri()
        val videoId04ExpectedObject = super.readJsonContentFromFile(fileQueryResultUri)

        val expectedVideoRequest = super.convertJsonContentStringToObject(
            videoId04ExpectedObject,
            super.buildTypeReference<Video>()
        )

        val expectedRepositoryQueryResult = super.convertJsonContentStringToObject(
            videoId04ExpectedObject,
            super.buildTypeReference<VideoEntity>()
        )

        `when`(videoRepository.save(any()))
            .thenReturn(expectedRepositoryQueryResult)

        val expectedSavedVideo = expectedRepositoryQueryResult.toVideo()
        val actualSavedVideo = insertVideoGateway.saveOne(expectedVideoRequest)

        assertEquals(expectedSavedVideo, actualSavedVideo)
        verify(videoRepository, times(1)).save(expectedVideoRequest.toVideoEntity())
    }
}