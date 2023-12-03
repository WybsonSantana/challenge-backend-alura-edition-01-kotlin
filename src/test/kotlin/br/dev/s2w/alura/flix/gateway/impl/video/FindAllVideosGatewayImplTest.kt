package br.dev.s2w.alura.flix.gateway.impl.video

import br.dev.s2w.alura.flix.domain.gateway.video.FindAllVideosGateway
import br.dev.s2w.alura.flix.gateway.repository.VideoRepository
import br.dev.s2w.alura.flix.gateway.repository.entity.VideoEntity
import br.dev.s2w.alura.flix.gateway.repository.mapper.VideoEntityMapper.toVideo
import br.dev.s2w.alura.flix.utility.GeneralBeans
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class FindAllVideosGatewayImplTest : GeneralBeans() {

    @Mock
    private lateinit var videoRepository: VideoRepository

    private lateinit var findAllVideosGateway: FindAllVideosGateway

    @BeforeEach
    fun setup() {
        findAllVideosGateway = FindAllVideosGatewayImpl(videoRepository)
    }

    @Test
    fun `should return all videos`() {
        val fileResponseUri = super.getAllVideosWeekQueryResultUri()
        val allVideosExpectedResponse = super.readJsonContentFromFile(fileResponseUri)

        val expectedRepositoryQueryResult = super.convertJsonContentStringToObject(
            allVideosExpectedResponse,
            super.buildTypeReference<List<VideoEntity>>()
        )

        `when`(videoRepository.findAll())
            .thenReturn(expectedRepositoryQueryResult)

        val expectedVideos = expectedRepositoryQueryResult.map { it.toVideo() }
        val actualVideos = findAllVideosGateway.fetch()

        assertEquals(expectedVideos, actualVideos)
        verify(videoRepository, times(1)).findAll()
    }

    @Test
    fun `should return an empty list`() {
        val expectedVideosEmptyList = listOf<VideoEntity>()

        `when`(videoRepository.findAll())
            .thenReturn(expectedVideosEmptyList)

        val actualEmptyVideosList = findAllVideosGateway.fetch()

        assertEquals(expectedVideosEmptyList, actualEmptyVideosList)
        verify(videoRepository, times(1)).findAll()
    }
}