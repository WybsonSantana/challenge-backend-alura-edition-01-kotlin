package br.dev.s2w.alura.flix.gateway.impl.video

import br.dev.s2w.alura.flix.domain.gateway.video.FindAllVideosByTituloGateway
import br.dev.s2w.alura.flix.gateway.repository.VideoRepository
import br.dev.s2w.alura.flix.gateway.repository.entity.VideoEntity
import br.dev.s2w.alura.flix.gateway.repository.mapper.VideoEntityMapper.toVideo
import br.dev.s2w.alura.flix.utility.GeneralBeans
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.eq
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

@ExtendWith(MockitoExtension::class)
internal class FindAllVideosByTituloGatewayImplTest : GeneralBeans() {

    @Mock
    private lateinit var videoRepository: VideoRepository

    @Mock
    private lateinit var pageable: Pageable

    private lateinit var findAllVideosByTituloGateway: FindAllVideosByTituloGateway

    @BeforeEach
    fun setup() {
        findAllVideosByTituloGateway = FindAllVideosByTituloGatewayImpl(videoRepository)
    }

    @Test
    fun `should return all videos with title containing Semana`() {
        val fileResponseUri = super.getAllVideosWeekQueryResultFileUri()
        val allVideosWeekExpectedResponse = super.readJsonContentFromFile(fileResponseUri)

        val expectedRepositoryQueryResult = super.convertJsonContentStringToObject(
            allVideosWeekExpectedResponse,
            super.buildTypeReference<List<VideoEntity>>()
        )

        `when`(videoRepository.findByTituloContaining(anyString(), eq(pageable)))
            .thenReturn(PageImpl(expectedRepositoryQueryResult))

        val titleForQuery = "Semana"

        val expectedVideos = expectedRepositoryQueryResult.map { it.toVideo() }
        val actualVideos = findAllVideosByTituloGateway.fetchByTitulo(titleForQuery, pageable).run { content }

        assertEquals(expectedVideos, actualVideos)
        verify(videoRepository, times(1)).findByTituloContaining(titleForQuery, pageable)
    }

    @Test
    fun `should return a video with title containing Semana 01`() {
        val fileResponseUri = super.getVideoWeek01QueryResultFileUri()
        val videoWeek01ExpectedResponse = super.readJsonContentFromFile(fileResponseUri)

        val expectedRepositoryQueryResult = super.convertJsonContentStringToObject(
            videoWeek01ExpectedResponse,
            super.buildTypeReference<List<VideoEntity>>()
        )

        `when`(videoRepository.findByTituloContaining(anyString(), eq(pageable)))
            .thenReturn(PageImpl(expectedRepositoryQueryResult))

        val titleForQuery = "Semana 01"

        val expectedVideos = expectedRepositoryQueryResult.map { it.toVideo() }
        val actualVideos = findAllVideosByTituloGateway.fetchByTitulo(titleForQuery, pageable).run { content }

        assertEquals(expectedVideos, actualVideos)
        verify(videoRepository, times(1)).findByTituloContaining(titleForQuery, pageable)
    }

    @Test
    fun `should return a video with title containing Semana 02`() {
        val fileResponseUri = super.getVideoWeek02QueryResultFileUri()
        val videoWeek02ExpectedResponse = super.readJsonContentFromFile(fileResponseUri)

        val expectedRepositoryQueryResult = super.convertJsonContentStringToObject(
            videoWeek02ExpectedResponse,
            super.buildTypeReference<List<VideoEntity>>()
        )

        `when`(videoRepository.findByTituloContaining(anyString(), eq(pageable)))
            .thenReturn(PageImpl(expectedRepositoryQueryResult))

        val titleForQuery = "Semana 02"

        val expectedVideos = expectedRepositoryQueryResult.map { it.toVideo() }
        val actualVideos = findAllVideosByTituloGateway.fetchByTitulo(titleForQuery, pageable).run { content }

        assertEquals(expectedVideos, actualVideos)
        verify(videoRepository, times(1)).findByTituloContaining(titleForQuery, pageable)
    }

    @Test
    fun `should return a video with title containing Semana 03`() {
        val fileResponseUri = super.getVideoWeek03QueryResultFileUri()
        val videoWeek03ExpectedResponse = super.readJsonContentFromFile(fileResponseUri)

        val expectedRepositoryQueryResult = super.convertJsonContentStringToObject(
            videoWeek03ExpectedResponse,
            super.buildTypeReference<List<VideoEntity>>()
        )

        `when`(videoRepository.findByTituloContaining(anyString(), eq(pageable)))
            .thenReturn(PageImpl(expectedRepositoryQueryResult))

        val titleForQuery = "Semana 03"

        val expectedVideos = expectedRepositoryQueryResult.map { it.toVideo() }
        val actualVideos = findAllVideosByTituloGateway.fetchByTitulo(titleForQuery, pageable).run { content }

        assertEquals(expectedVideos, actualVideos)
        verify(videoRepository, times(1)).findByTituloContaining(titleForQuery, pageable)
    }

    @Test
    fun `should return an empty list when the search does not find any video with the searched title`() {
        `when`(videoRepository.findByTituloContaining(anyString(), eq(pageable)))
            .thenReturn(PageImpl(emptyList()))

        val titleForQuery = "Semana 05"

        val actualEmptyVideosList = findAllVideosByTituloGateway.fetchByTitulo(titleForQuery, pageable).run { content }

        assertTrue(actualEmptyVideosList.isEmpty())
        verify(videoRepository, times(1)).findByTituloContaining(titleForQuery, pageable)
    }

}