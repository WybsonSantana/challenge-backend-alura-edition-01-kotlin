package br.dev.s2w.alura.flix.adapter.controller

import br.dev.s2w.alura.flix.gateway.repository.CategoriaRepository
import br.dev.s2w.alura.flix.infrastructure.utility.Constants
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.ARGUMENT_NOT_VALID_MESSAGE
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.CATEGORIA_IN_USE_EXCEPTION_MESSAGE
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.CATEGORIA_NOT_FOUND_EXCEPTION_MESSAGE
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.CATEGORIA_V1_API_PATH
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.HTTP_MESSAGE_NOT_READABLE
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.INTERNAL_SERVER_ERROR_MESSAGE
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.LOCAL_HOST
import br.dev.s2w.alura.flix.utility.GeneralBeans
import org.junit.jupiter.api.Test
import org.mockito.Mockito.anyLong
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.transaction.annotation.Transactional
import javax.persistence.PersistenceException

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("qa")
internal class CategoriaControllerTest : GeneralBeans() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @SpyBean
    private lateinit var categoriaRepository: CategoriaRepository

    @Test
    fun `should return all categories when status is 200 ok`() {
        val fileResponseUri = super.getAllCategoriesResponseFileUri()
        val allCategoriesExpectedResponse = super.readJsonContentFromFile(fileResponseUri)

        mockMvc.perform(
            MockMvcRequestBuilders.get(CATEGORIA_V1_API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(allCategoriesExpectedResponse))
    }

    @Test
    fun `should return free category when status is 200 ok`() {
        val fileResponseUri = super.getFreeCategoryResponseFileUri()
        val freeCategoryExpectedResponse = super.readJsonContentFromFile(fileResponseUri)

        mockMvc.perform(
            MockMvcRequestBuilders.get(CATEGORIA_V1_API_PATH.plus("/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(freeCategoryExpectedResponse))
    }

    @Test
    fun `should return back-end category when status is 200 ok`() {
        val fileResponseUri = super.getBackEndCategoryResponseFileUri()
        val backEndCategoryExpectedResponse = super.readJsonContentFromFile(fileResponseUri)

        mockMvc.perform(
            MockMvcRequestBuilders.get(CATEGORIA_V1_API_PATH.plus("/2"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(backEndCategoryExpectedResponse))
    }

    @Test
    fun `should return data base category when status is 200 ok`() {
        val fileResponseUri = super.getDataBaseCategoryResponseFileUri()
        val dataBaseCategoryExpectedResponse = super.readJsonContentFromFile(fileResponseUri)

        mockMvc.perform(
            MockMvcRequestBuilders.get(CATEGORIA_V1_API_PATH.plus("/3"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(dataBaseCategoryExpectedResponse))
    }

    @Test
    @Transactional
    fun `should save cloud category when status is 201 created`() {
        val fileRequestUri = super.getCloudCategoryRequestFileUri()
        val cloudCategoryExpectedRequest = super.readJsonContentFromFile(fileRequestUri)

        val fileResponseUri = super.getCloudCategoryResponseFileUri()
        val cloudCategoryExpectedResponse = super.readJsonContentFromFile(fileResponseUri)

        mockMvc.perform(
            MockMvcRequestBuilders.post(CATEGORIA_V1_API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(cloudCategoryExpectedRequest)
        )
            .andExpect(
                MockMvcResultMatchers.header()
                    .string("Location", LOCAL_HOST.plus(CATEGORIA_V1_API_PATH).plus("/4"))
            )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(cloudCategoryExpectedResponse))
    }

    @Test
    @Transactional
    fun `should update data base category when status is 200 ok`() {
        val fileRequestUri = super.getDataBaseCategoryUpdateRequestFileUri()
        val dataBaseCategoryExpectedUpdateRequest = super.readJsonContentFromFile(fileRequestUri)

        val fileResponseUri = super.getDataBaseCategoryUpdatedResponseFileUri()
        val dataBaseCategoryExpectedUpdatedResponse = super.readJsonContentFromFile(fileResponseUri)

        mockMvc.perform(
            MockMvcRequestBuilders.put(CATEGORIA_V1_API_PATH.plus("/3"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(dataBaseCategoryExpectedUpdateRequest)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))

        mockMvc.perform(
            MockMvcRequestBuilders.get(CATEGORIA_V1_API_PATH.plus("/3"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(dataBaseCategoryExpectedUpdatedResponse))
    }

    @Test
    @Transactional
    fun `should delete data base category when status is 204 no content`() {
        mockMvc.perform(
            MockMvcRequestBuilders.delete(CATEGORIA_V1_API_PATH.plus("/3"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNoContent)

        mockMvc.perform(
            MockMvcRequestBuilders.get(CATEGORIA_V1_API_PATH.plus("/3"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.message").value(CATEGORIA_NOT_FOUND_EXCEPTION_MESSAGE)
            )
    }

    @Test
    fun `should return status code 404 when trying to retrieve a category with a non-existent ID`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get(CATEGORIA_V1_API_PATH.plus("/10"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.message").value(CATEGORIA_NOT_FOUND_EXCEPTION_MESSAGE)
            )
    }

    @Test
    fun `should return status code 400 when some category request field is blank`() {
        val fileRequestUri = super.getBlankFieldCategoryRequestFileUri()
        val blankFieldExpectedRequest = super.readJsonContentFromFile(fileRequestUri)

        mockMvc.perform(
            MockMvcRequestBuilders.post(CATEGORIA_V1_API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(blankFieldExpectedRequest)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(ARGUMENT_NOT_VALID_MESSAGE))
    }

    @Test
    fun `should return status code 400 when trying to save a category with non-existent or invalid json`() {
        mockMvc.perform(
            MockMvcRequestBuilders.post(CATEGORIA_V1_API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{}")
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(HTTP_MESSAGE_NOT_READABLE))
    }

    @Test
    fun `should return status code 404 when trying to update a category with a non-existent ID`() {
        val fileRequestUri = super.getDataBaseCategoryUpdatedResponseFileUri()
        val dataBaseExpectedRequest = super.readJsonContentFromFile(fileRequestUri)

        mockMvc.perform(
            MockMvcRequestBuilders.put(CATEGORIA_V1_API_PATH.plus("/10"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(dataBaseExpectedRequest)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.message").value(CATEGORIA_NOT_FOUND_EXCEPTION_MESSAGE)
            )
    }

    @Test
    fun `should return status code 404 when trying to delete a category with a non-existent ID`() {
        mockMvc.perform(
            MockMvcRequestBuilders.delete(CATEGORIA_V1_API_PATH.plus("/10"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.message").value(CATEGORIA_NOT_FOUND_EXCEPTION_MESSAGE)
            )
    }

    @Test
    fun `should return status code 422 when trying to delete a category in use`() {
        mockMvc.perform(
            MockMvcRequestBuilders.delete(CATEGORIA_V1_API_PATH.plus("/2"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isConflict)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(CATEGORIA_IN_USE_EXCEPTION_MESSAGE))
    }

    @Test
    fun `should return status code 500 when data base connection fail`() {
        `when`(categoriaRepository.findById(anyLong()))
            .thenThrow(PersistenceException("Database transaction failed!"))

        mockMvc.perform(
            MockMvcRequestBuilders.get(CATEGORIA_V1_API_PATH.plus("/9"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isInternalServerError)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(INTERNAL_SERVER_ERROR_MESSAGE))
    }
}