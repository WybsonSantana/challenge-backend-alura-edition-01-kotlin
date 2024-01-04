package br.dev.s2w.alura.flix.gateway.impl.video

import br.dev.s2w.alura.flix.domain.gateway.video.FindAllVideosGateway
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
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

@ExtendWith(MockitoExtension::class)
internal class FindAllVideosGatewayImplTest : GeneralBeans() {

    @Mock
    private lateinit var videoRepository: VideoRepository

    @Mock
    private lateinit var pageable: Pageable

    private lateinit var findAllVideosGateway: FindAllVideosGateway

    @BeforeEach
    fun setup() {
        findAllVideosGateway = FindAllVideosGatewayImpl(videoRepository)
    }

    @Test
    fun `should return all videos`() {
        val fileResponseUri = super.getAllVideosWeekQueryResultFileUri()
        val allVideosExpectedResponse = super.readJsonContentFromFile(fileResponseUri)

        val expectedRepositoryQueryResult = super.convertJsonContentStringToObject(
            allVideosExpectedResponse,
            super.buildTypeReference<List<VideoEntity>>()
        )

        `when`(videoRepository.findAll(eq(pageable)))
            .thenReturn(PageImpl(expectedRepositoryQueryResult))

        val expectedVideos = expectedRepositoryQueryResult.map { it.toVideo() }
        val actualVideos = findAllVideosGateway.fetch(pageable).run { content }

        assertEquals(expectedVideos, actualVideos)
        verify(videoRepository, times(1)).findAll(pageable)
    }

    @Test
    fun `should return an empty list`() {
        `when`(videoRepository.findAll(eq(pageable)))
            .thenReturn(PageImpl(emptyList()))

        val actualEmptyVideosList = findAllVideosGateway.fetch(pageable).run { content }

        assertTrue(actualEmptyVideosList.isEmpty())
        verify(videoRepository, times(1)).findAll(pageable)
    }
}