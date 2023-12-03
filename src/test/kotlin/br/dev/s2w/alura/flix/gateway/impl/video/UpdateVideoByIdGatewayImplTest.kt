package br.dev.s2w.alura.flix.gateway.impl.video

import br.dev.s2w.alura.flix.domain.exception.VideoNotFoundException
import br.dev.s2w.alura.flix.domain.gateway.video.UpdateVideoByIdGateway
import br.dev.s2w.alura.flix.domain.model.Video
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

@ExtendWith(MockitoExtension::class)
internal class UpdateVideoByIdGatewayImplTest : GeneralBeans() {

    @Mock
    private lateinit var videoRepository: VideoRepository

    private lateinit var updateVideoByIdGateway: UpdateVideoByIdGateway

    @BeforeEach
    fun setup() {
        updateVideoByIdGateway = UpdateVideoByIdGatewayImpl(videoRepository)
    }

    @Test
    fun `should update video ID 03`() {
        val fileReferencedUri = super.getVideoId03QueryResultUri()
        val videoId03ExpectedReferenced = super.readJsonContentFromFile(fileReferencedUri)

        val fileQueryResultUri = super.getVideoId03UpdatedQueryResultUri()
        val videoId03ExpectedUpdatedObject = super.readJsonContentFromFile(fileQueryResultUri)

        val expectedVideoRequest = super.convertJsonContentStringToObject(
            videoId03ExpectedUpdatedObject,
            super.buildTypeReference<Video>()
        )

        val expectedVideoId03Referenced = super.convertJsonContentStringToObject(
            videoId03ExpectedReferenced,
            super.buildTypeReference<VideoEntity>()
        )

        val expectedRepositoryQueryResult = super.convertJsonContentStringToObject(
            videoId03ExpectedUpdatedObject,
            super.buildTypeReference<VideoEntity>()
        )

        `when`(videoRepository.getReferenceById(anyLong()))
            .thenReturn(expectedVideoId03Referenced)

        `when`(videoRepository.save(any()))
            .thenReturn(expectedRepositoryQueryResult)

        val id = 3L

        val expectedUpdatedVideo = expectedRepositoryQueryResult.toVideo()
        val actualUpdatedVideo = updateVideoByIdGateway.modifyOneBy(id, expectedVideoRequest)

        assertEquals(expectedUpdatedVideo, actualUpdatedVideo)
        verify(videoRepository, times(1)).getReferenceById(id)
        verify(videoRepository, times(1)).save(expectedRepositoryQueryResult)
    }

    @Test
    fun `should throw a not found exception when the video ID is non-existent`() {
        val fileQueryResultUri = super.getVideoId03UpdatedQueryResultUri()
        val videoId03ExpectedUpdatedObject = super.readJsonContentFromFile(fileQueryResultUri)

        val expectedVideoRequest = super.convertJsonContentStringToObject(
            videoId03ExpectedUpdatedObject,
            super.buildTypeReference<Video>()
        )

        `when`(videoRepository.getReferenceById(anyLong()))
            .thenThrow(VideoNotFoundException(VIDEO_NOT_FOUND_EXCEPTION_MESSAGE))

        val id = 4L

        val thrownException = assertThrows<VideoNotFoundException> {
            updateVideoByIdGateway.modifyOneBy(id, expectedVideoRequest)
        }

        assertEquals(VIDEO_NOT_FOUND_EXCEPTION_MESSAGE, thrownException.message)
        verify(videoRepository, times(1)).getReferenceById(id)
        verify(videoRepository, times(0)).save(any())
    }
}