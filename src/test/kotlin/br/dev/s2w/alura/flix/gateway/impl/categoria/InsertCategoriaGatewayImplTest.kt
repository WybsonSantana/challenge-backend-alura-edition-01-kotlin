package br.dev.s2w.alura.flix.gateway.impl.categoria

import br.dev.s2w.alura.flix.domain.gateway.categoria.InsertCategoriaGateway
import br.dev.s2w.alura.flix.domain.model.Categoria
import br.dev.s2w.alura.flix.gateway.repository.CategoriaRepository
import br.dev.s2w.alura.flix.gateway.repository.entity.CategoriaEntity
import br.dev.s2w.alura.flix.gateway.repository.mapper.CategoriaEntityMapper.toCategoria
import br.dev.s2w.alura.flix.gateway.repository.mapper.CategoriaEntityMapper.toCategoriaEntity
import br.dev.s2w.alura.flix.utility.GeneralBeans
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class InsertCategoriaGatewayImplTest : GeneralBeans() {

    @Mock
    private lateinit var categoriaRepository: CategoriaRepository

    private lateinit var insertCategoriaGateway: InsertCategoriaGateway

    @BeforeEach
    fun setup() {
        insertCategoriaGateway = InsertCategoriaGatewayImpl(categoriaRepository)
    }

    @Test
    fun `should save cloud category`() {
        val fileRequestUri = super.getCloudCategoryRequestFileUri()
        val cloudCategoryExpectedRequest = super.readJsonContentFromFile(fileRequestUri)

        val fileResponseUri = super.getCloudCategoryResponseFileUri()
        val cloudCategoryExpectedResponse = super.readJsonContentFromFile(fileResponseUri)

        val expectedCategoryObjectRequest = super.convertJsonContentStringToObject(
            cloudCategoryExpectedRequest,
            super.buildTypeReference<Categoria>()
        )

        val expectedRepositoryQueryResult = super.convertJsonContentStringToObject(
            cloudCategoryExpectedResponse,
            super.buildTypeReference<CategoriaEntity>()
        )

        `when`(categoriaRepository.save(any()))
            .thenReturn(expectedRepositoryQueryResult)

        val expectedSavedCategory = expectedRepositoryQueryResult.toCategoria()
        val actualSavedCategory = insertCategoriaGateway.saveOne(expectedCategoryObjectRequest)

        assertEquals(expectedSavedCategory, actualSavedCategory)
        verify(categoriaRepository, times(1)).save(expectedCategoryObjectRequest.toCategoriaEntity())
    }
}