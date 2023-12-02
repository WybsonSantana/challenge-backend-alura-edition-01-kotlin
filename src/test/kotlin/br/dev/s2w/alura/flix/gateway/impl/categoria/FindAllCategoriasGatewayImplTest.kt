package br.dev.s2w.alura.flix.gateway.impl.categoria

import br.dev.s2w.alura.flix.domain.gateway.categoria.FindAllCategoriasGateway
import br.dev.s2w.alura.flix.domain.model.Categoria
import br.dev.s2w.alura.flix.gateway.repository.CategoriaRepository
import br.dev.s2w.alura.flix.gateway.repository.entity.CategoriaEntity
import br.dev.s2w.alura.flix.gateway.repository.mapper.CategoriaEntityMapper.toCategoria
import br.dev.s2w.alura.flix.utility.GeneralBeans
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class FindAllCategoriasGatewayImplTest : GeneralBeans() {

    @Mock
    private lateinit var categoriaRepository: CategoriaRepository

    private lateinit var findAllCategoriasGateway: FindAllCategoriasGateway

    @BeforeEach
    fun setup() {
        findAllCategoriasGateway = FindAllCategoriasGatewayImpl(categoriaRepository)
    }

    @Test
    fun `should return all categories`() {
        val fileResponseUri = super.getAllCategoriesResponseFileUri()
        val allCategoriesExpectedResponse = super.readJsonContentFromFile(fileResponseUri)

        val expectedRepositoryQueryResult = super.convertJsonContentStringToObject(
            allCategoriesExpectedResponse,
            super.buildTypeReference<List<CategoriaEntity>>()
        )

        `when`(categoriaRepository.findAll())
            .thenReturn(expectedRepositoryQueryResult)

        val expectedCategories = expectedRepositoryQueryResult.map { it.toCategoria() }
        val actualCategories: List<Categoria> = findAllCategoriasGateway.fetch()

        assertEquals(expectedCategories, actualCategories)
        verify(categoriaRepository, times(1)).findAll()
    }

    @Test
    fun `should return a empty list`() {
        val expectedCategoriesEmptyList = listOf<CategoriaEntity>()

        `when`(categoriaRepository.findAll())
            .thenReturn(expectedCategoriesEmptyList)

        val actualEmptyCategoriesList = findAllCategoriasGateway.fetch()

        assertEquals(expectedCategoriesEmptyList, actualEmptyCategoriesList)
        verify(categoriaRepository, times(1)).findAll()
    }
}