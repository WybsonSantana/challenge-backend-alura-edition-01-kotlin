package br.dev.s2w.alura.flix.gateway.impl.categoria

import br.dev.s2w.alura.flix.domain.exception.CategoriaInUseException
import br.dev.s2w.alura.flix.domain.exception.CategoriaNotFoundException
import br.dev.s2w.alura.flix.domain.gateway.categoria.DeleteCategoriaByIdGateway
import br.dev.s2w.alura.flix.gateway.repository.CategoriaRepository
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.CATEGORIA_IN_USE_EXCEPTION_MESSAGE
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.CATEGORIA_NOT_FOUND_EXCEPTION_MESSAGE
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
internal class DeleteCategoriaByIdGatewayImplTest : GeneralBeans() {

    @Mock
    private lateinit var categoriaRepository: CategoriaRepository

    private lateinit var deleteCategoriaByIdGateway: DeleteCategoriaByIdGateway

    @BeforeEach
    fun setup() {
        deleteCategoriaByIdGateway = DeleteCategoriaByIdGatewayImpl(categoriaRepository)
    }

    @Test
    fun `should delete a category if it is not in use`() {
        doNothing()
            .`when`(categoriaRepository).deleteById(anyLong())

        val id = 3L

        assertDoesNotThrow {
            deleteCategoriaByIdGateway.removeOneBy(id)
        }

        verify(categoriaRepository, times(1)).deleteById(id)
    }

    @Test
    fun `should throw a not found exception when trying to delete a non-existent category`() {
        `when`(categoriaRepository.deleteById(anyLong()))
            .thenThrow(CategoriaNotFoundException(CATEGORIA_NOT_FOUND_EXCEPTION_MESSAGE))

        val id = 4L

        val thrownException = assertThrows<CategoriaNotFoundException> {
            deleteCategoriaByIdGateway.removeOneBy(id)
        }

        assertEquals(CATEGORIA_NOT_FOUND_EXCEPTION_MESSAGE, thrownException.message)
        verify(categoriaRepository, times(1)).deleteById(id)
    }

    @Test
    fun `should throw a category in use exception when trying to delete a category in use`() {
        `when`(categoriaRepository.deleteById(anyLong()))
            .thenThrow(CategoriaInUseException(CATEGORIA_IN_USE_EXCEPTION_MESSAGE))

        val id = 2L

        val thrownException = assertThrows<CategoriaInUseException> {
            deleteCategoriaByIdGateway.removeOneBy(id)
        }

        assertEquals(CATEGORIA_IN_USE_EXCEPTION_MESSAGE, thrownException.message)
        verify(categoriaRepository, times(1)).deleteById(id)
    }
}