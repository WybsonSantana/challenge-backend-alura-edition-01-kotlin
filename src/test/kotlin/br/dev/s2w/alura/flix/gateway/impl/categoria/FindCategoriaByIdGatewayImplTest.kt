package br.dev.s2w.alura.flix.gateway.impl.categoria

import br.dev.s2w.alura.flix.domain.exception.CategoriaNotFoundException
import br.dev.s2w.alura.flix.domain.gateway.categoria.FindCategoriaByIdGateway
import br.dev.s2w.alura.flix.gateway.repository.CategoriaRepository
import br.dev.s2w.alura.flix.gateway.repository.entity.CategoriaEntity
import br.dev.s2w.alura.flix.gateway.repository.mapper.CategoriaEntityMapper.toCategoria
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.CATEGORIA_NOT_FOUND_EXCEPTION_MESSAGE
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
internal class FindCategoriaByIdGatewayImplTest : GeneralBeans() {

    @Mock
    private lateinit var categoriaRepository: CategoriaRepository

    private lateinit var findCategoriaByIdGateway: FindCategoriaByIdGateway

    @BeforeEach
    fun setup() {
        findCategoriaByIdGateway = FindCategoriaByIdGatewayImpl(categoriaRepository)
    }

    @Test
    fun `should return free category`() {
        val fileResponseUri = super.getFreeCategoryResponseFileUri()
        val freeCategoryExpectedResponse = super.readJsonContentFromFile(fileResponseUri)

        val expectedRepositoryQueryResult = super.convertJsonContentStringToObject(
            freeCategoryExpectedResponse,
            super.buildTypeReference<CategoriaEntity>()
        )

        `when`(categoriaRepository.findById(anyLong()))
            .thenReturn(Optional.of(expectedRepositoryQueryResult))

        val id = 1L

        val expectedCategory = expectedRepositoryQueryResult.toCategoria()
        val actualCategory = findCategoriaByIdGateway.fetchOneBy(id)

        assertEquals(expectedCategory, actualCategory)
        verify(categoriaRepository, times(1)).findById(id)
    }

    @Test
    fun `should return back-end category`() {
        val fileResponseUri = super.getBackEndCategoryResponseFileUri()
        val freeCategoryExpectedResponse = super.readJsonContentFromFile(fileResponseUri)

        val expectedRepositoryQueryResult = super.convertJsonContentStringToObject(
            freeCategoryExpectedResponse,
            super.buildTypeReference<CategoriaEntity>()
        )

        `when`(categoriaRepository.findById(anyLong()))
            .thenReturn(Optional.of(expectedRepositoryQueryResult))

        val id = 2L

        val expectedCategory = expectedRepositoryQueryResult.toCategoria()
        val actualCategory = findCategoriaByIdGateway.fetchOneBy(id)

        assertEquals(expectedCategory, actualCategory)
        verify(categoriaRepository, times(1)).findById(id)
    }

    @Test
    fun `should return data base category`() {
        val fileResponseUri = super.getDataBaseCategoryResponseFileUri()
        val freeCategoryExpectedResponse = super.readJsonContentFromFile(fileResponseUri)

        val expectedRepositoryQueryResult = super.convertJsonContentStringToObject(
            freeCategoryExpectedResponse,
            super.buildTypeReference<CategoriaEntity>()
        )

        `when`(categoriaRepository.findById(anyLong()))
            .thenReturn(Optional.of(expectedRepositoryQueryResult))

        val id = 3L

        val expectedCategory = expectedRepositoryQueryResult.toCategoria()
        val actualCategory = findCategoriaByIdGateway.fetchOneBy(id)

        assertEquals(expectedCategory, actualCategory)
        verify(categoriaRepository, times(1)).findById(id)
    }

    @Test
    fun `should throw a not found exception when the category ID is non-existent`() {
        `when`(categoriaRepository.findById(anyLong()))
            .thenThrow(CategoriaNotFoundException(CATEGORIA_NOT_FOUND_EXCEPTION_MESSAGE))

        val id = 4L

        val thrownException = assertThrows<CategoriaNotFoundException> {
            findCategoriaByIdGateway.fetchOneBy(id)
        }

        assertEquals(CATEGORIA_NOT_FOUND_EXCEPTION_MESSAGE, thrownException.message)
        verify(categoriaRepository, times(1)).findById(id)
    }
}