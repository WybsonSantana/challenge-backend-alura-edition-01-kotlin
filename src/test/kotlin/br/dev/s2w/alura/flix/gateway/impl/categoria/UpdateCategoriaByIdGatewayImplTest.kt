package br.dev.s2w.alura.flix.gateway.impl.categoria

import br.dev.s2w.alura.flix.domain.exception.CategoriaNotFoundException
import br.dev.s2w.alura.flix.domain.gateway.categoria.UpdateCategoriaByIdGateway
import br.dev.s2w.alura.flix.domain.model.Categoria
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

@ExtendWith(MockitoExtension::class)
internal class UpdateCategoriaByIdGatewayImplTest : GeneralBeans() {

    @Mock
    private lateinit var categoriaRepository: CategoriaRepository

    private lateinit var updateCategoriaByIdGateway: UpdateCategoriaByIdGateway

    @BeforeEach
    fun setup() {
        updateCategoriaByIdGateway = UpdateCategoriaByIdGatewayImpl(categoriaRepository)
    }

    @Test
    fun `should update data base category`() {
        val fileRequestUri = super.getDataBaseCategoryUpdateRequestFileUri()
        val dataBaseCategoryExpectedUpdateRequest = super.readJsonContentFromFile(fileRequestUri)

        val fileReferencedUri = super.getDataBaseCategoryResponseFileUri()
        val dataBaseCategoryExpectedRefferenced = super.readJsonContentFromFile(fileReferencedUri)

        val fileResponseUri = super.getDataBaseCategoryUpdatedResponseFileUri()
        val dataBaseCategoryExpectedUpdatedResponse = super.readJsonContentFromFile(fileResponseUri)

        val expectedCategoryObjectRequest = super.convertJsonContentStringToObject(
            dataBaseCategoryExpectedUpdateRequest,
            super.buildTypeReference<Categoria>()
        )

        val expectedCategoryObjectReferenced = super.convertJsonContentStringToObject(
            dataBaseCategoryExpectedRefferenced,
            super.buildTypeReference<CategoriaEntity>()
        )

        val expectedRepositoryQueryResult = super.convertJsonContentStringToObject(
            dataBaseCategoryExpectedUpdatedResponse,
            super.buildTypeReference<CategoriaEntity>()
        )

        `when`(categoriaRepository.getReferenceById(anyLong()))
            .thenReturn(expectedCategoryObjectReferenced)

        `when`(categoriaRepository.save(any()))
            .thenReturn(expectedRepositoryQueryResult)

        val id = 3L

        val expectedUpdatedCategory = expectedRepositoryQueryResult.toCategoria()
        val actualUpdatedCategory = updateCategoriaByIdGateway.modifyOne(id, expectedCategoryObjectRequest)

        assertEquals(expectedUpdatedCategory, actualUpdatedCategory)
        verify(categoriaRepository, times(1)).getReferenceById(id)
        verify(categoriaRepository, times(1)).save(expectedRepositoryQueryResult)
    }

    @Test
    fun `should throw a not found exception when the category ID is non-existent`() {
        val fileRequestUri = super.getDataBaseCategoryUpdateRequestFileUri()
        val dataBaseCategoryExpectedUpdateRequest = super.readJsonContentFromFile(fileRequestUri)

        val expectedCategoryObjectRequest = super.convertJsonContentStringToObject(
            dataBaseCategoryExpectedUpdateRequest,
            super.buildTypeReference<Categoria>()
        )

        `when`(categoriaRepository.getReferenceById(anyLong()))
            .thenThrow(CategoriaNotFoundException(CATEGORIA_NOT_FOUND_EXCEPTION_MESSAGE))

        val id = 4L

        val thrownException = assertThrows<CategoriaNotFoundException> {
            updateCategoriaByIdGateway.modifyOne(id, expectedCategoryObjectRequest)
        }

        assertEquals(CATEGORIA_NOT_FOUND_EXCEPTION_MESSAGE, thrownException.message)
        verify(categoriaRepository, times(1)).getReferenceById(id)
        verify(categoriaRepository, times(0)).save(any())
    }
}