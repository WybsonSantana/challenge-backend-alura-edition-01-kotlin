package br.dev.s2w.alura.flix.gateway.impl.video

import br.dev.s2w.alura.flix.domain.exception.VideoNotFoundException
import br.dev.s2w.alura.flix.domain.gateway.video.FindVideoByIdGateway
import br.dev.s2w.alura.flix.gateway.repository.VideoRepository
import br.dev.s2w.alura.flix.gateway.repository.entity.VideoEntity
import br.dev.s2w.alura.flix.gateway.repository.mapper.VideoEntityMapper.toVideo
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.VIDEO_NOT_FOUND_EXCEPTION_MESSAGE
import br.dev.s2w.alura.flix.utility.GeneralBeans
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class FindVideoByIdGatewayImplTest : GeneralBeans() {

    @Mock
    private lateinit var videoRepository: VideoRepository

    private lateinit var findVideoByIdGateway: FindVideoByIdGateway

    @BeforeEach
    fun setup() {
        findVideoByIdGateway = FindVideoByIdGatewayImpl(videoRepository)
    }

    @Test
    fun `should return video ID 01`() {
        val fileResponseUri = super.getVideoId01QueryResultFileUri()
        val videoId01ExpectedResponse = super.readJsonContentFromFile(fileResponseUri)

        val expectedRepositoryQueryResult = super.convertJsonContentStringToObject(
            videoId01ExpectedResponse,
            super.buildTypeReference<VideoEntity>()
        )

        `when`(videoRepository.findById(anyLong()))
            .thenReturn(Optional.of(expectedRepositoryQueryResult))

        val id = 1L

        val expectedVideo = expectedRepositoryQueryResult.toVideo()
        val actualVideo = findVideoByIdGateway.fetchOneBy(id)

        assertEquals(expectedVideo, actualVideo)
        verify(videoRepository, times(1)).findById(id)
    }

    @Test
    fun `should return video ID 02`() {
        val fileResponseUri = super.getVideoId02QueryResultFileUri()
        val videoId02ExpectedResponse = super.readJsonContentFromFile(fileResponseUri)

        val expectedRepositoryQueryResult = super.convertJsonContentStringToObject(
            videoId02ExpectedResponse,
            super.buildTypeReference<VideoEntity>()
        )

        `when`(videoRepository.findById(anyLong()))
            .thenReturn(Optional.of(expectedRepositoryQueryResult))

        val id = 2L

        val expectedVideo = expectedRepositoryQueryResult.toVideo()
        val actualVideo = findVideoByIdGateway.fetchOneBy(id)

        assertEquals(expectedVideo, actualVideo)
        verify(videoRepository, times(1)).findById(id)
    }

    @Test
    fun `should return video ID 03`() {
        val fileResponseUri = super.getVideoId03QueryResultFileUri()
        val videoId03ExpectedResponse = super.readJsonContentFromFile(fileResponseUri)

        val expectedRepositoryQueryResult = super.convertJsonContentStringToObject(
            videoId03ExpectedResponse,
            super.buildTypeReference<VideoEntity>()
        )

        `when`(videoRepository.findById(anyLong()))
            .thenReturn(Optional.of(expectedRepositoryQueryResult))

        val id = 3L

        val expectedVideo = expectedRepositoryQueryResult.toVideo()
        val actualVideo = findVideoByIdGateway.fetchOneBy(id)

        assertEquals(expectedVideo, actualVideo)
        verify(videoRepository, times(1)).findById(id)
    }

    @Test
    fun `should throw a not found exception when the video ID is non-existent`() {
        `when`(videoRepository.findById(anyLong()))
            .thenThrow(VideoNotFoundException(VIDEO_NOT_FOUND_EXCEPTION_MESSAGE))

        val id = 4L

        val thrownException = assertThrows<VideoNotFoundException> {
            findVideoByIdGateway.fetchOneBy(id)
        }

        assertEquals(VIDEO_NOT_FOUND_EXCEPTION_MESSAGE, thrownException.message)
        verify(videoRepository, times(1)).findById(id)
    }
}