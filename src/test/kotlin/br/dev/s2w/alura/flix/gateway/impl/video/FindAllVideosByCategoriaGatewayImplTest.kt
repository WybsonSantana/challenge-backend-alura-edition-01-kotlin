package br.dev.s2w.alura.flix.gateway.impl.video

import br.dev.s2w.alura.flix.domain.gateway.video.FindAllVideosByCategoriaGateway
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
internal class FindAllVideosByCategoriaGatewayImplTest : GeneralBeans() {

    @Mock
    private lateinit var videoRepository: VideoRepository

    private lateinit var findAllVideosByCategoriaGateway: FindAllVideosByCategoriaGateway

    @BeforeEach
    fun setup() {
        findAllVideosByCategoriaGateway = FindAllVideosByCategoriaGatewayImpl(videoRepository)
    }

    @Test
    fun `should return all videos with category ID 02`() {
        val fileResponseUri = super.getAllVideosWeekQueryResultFileUri()
        val allVideosCategoryId02ExpectedResponse = super.readJsonContentFromFile(fileResponseUri)

        val expectedRepositoryQueryResult = super.convertJsonContentStringToObject(
            allVideosCategoryId02ExpectedResponse,
            super.buildTypeReference<List<VideoEntity>>()
        )

        `when`(videoRepository.findByCategoriaId(anyLong()))
            .thenReturn(expectedRepositoryQueryResult)

        val id = 2L

        val expectedVideos = expectedRepositoryQueryResult.map { it.toVideo() }
        val actualVideos = findAllVideosByCategoriaGateway.fetchByCategoria(id)

        assertEquals(expectedVideos, actualVideos)
        verify(videoRepository, times(1)).findByCategoriaId(id)
    }

    @Test
    fun `should return an empty list when the category does not contain videos or does not exist`() {
        val expectedVideosEmptyList = listOf<VideoEntity>()

        `when`(videoRepository.findByCategoriaId(anyLong()))
            .thenReturn(expectedVideosEmptyList)

        val id = 4L

        val actualEmptyVideosList = findAllVideosByCategoriaGateway.fetchByCategoria(id)

        assertEquals(expectedVideosEmptyList, actualEmptyVideosList)
        verify(videoRepository, times(1)).findByCategoriaId(id)
    }
}