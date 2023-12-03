package br.dev.s2w.alura.flix.gateway.impl.video

import br.dev.s2w.alura.flix.domain.exception.VideoNotFoundException
import br.dev.s2w.alura.flix.domain.gateway.video.DeleteVideoByIdGateway
import br.dev.s2w.alura.flix.gateway.repository.VideoRepository
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.VIDEO_NOT_FOUND_EXCEPTION_MESSAGE
import br.dev.s2w.alura.flix.utility.GeneralBeans
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class DeleteVideoByIdGatewayImplTest : GeneralBeans() {

    @Mock
    private lateinit var videoRepository: VideoRepository

    private lateinit var deleteVideobyIdGateway: DeleteVideoByIdGateway

    @BeforeEach
    fun setup() {
        deleteVideobyIdGateway = DeleteVideoByIdGatewayImpl(videoRepository)
    }

    @Test
    fun `should delete a video if it exists`() {
        doNothing()
            .`when`(videoRepository).deleteById(anyLong())

        val id = 3L

        assertDoesNotThrow {
            deleteVideobyIdGateway.removeOneBy(id)
        }

        verify(videoRepository, times(1)).deleteById(id)
    }

    @Test
    fun `should throw a not found exception when trying to delete a non-existent video`() {
        `when`(videoRepository.deleteById(anyLong()))
            .thenThrow(VideoNotFoundException(VIDEO_NOT_FOUND_EXCEPTION_MESSAGE))

        val id = 4L

        val thrownException = assertThrows<VideoNotFoundException> {
            deleteVideobyIdGateway.removeOneBy(id)
        }

        assertEquals(VIDEO_NOT_FOUND_EXCEPTION_MESSAGE, thrownException.message)
        verify(videoRepository, times(1)).deleteById(id)
    }
}