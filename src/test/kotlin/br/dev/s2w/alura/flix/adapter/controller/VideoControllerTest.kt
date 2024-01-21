package br.dev.s2w.alura.flix.adapter.controller

import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.gateway.repository.VideoRepository
import br.dev.s2w.alura.flix.infrastructure.utility.Constants
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.LOCAL_HOST
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.VIDEO_NOT_FOUND_EXCEPTION_MESSAGE
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.VIDEO_V1_API_PATH
import br.dev.s2w.alura.flix.utility.GeneralBeans
import kotlinx.serialization.json.*
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.mockito.Mockito.anyLong
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.http.HttpHeaders
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
internal class VideoControllerTest : GeneralBeans() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @SpyBean
    private lateinit var videoRepository: VideoRepository

    @Test
    fun `should return all videos when status is 200 ok`() {
        val fileResponseUri = super.getAllVideosResponseFileUri()
        val allVideosExpectedResponse = super.readJsonContentFromFile(fileResponseUri)
        val allVideosExpectedJsonArray = Json.parseToJsonElement(allVideosExpectedResponse).jsonArray

        mockMvc.perform(
            MockMvcRequestBuilders.get(VIDEO_V1_API_PATH)
                .header(HttpHeaders.AUTHORIZATION, super.buildBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id", Matchers.equalTo(allVideosExpectedJsonArray[0].jsonObject["id"]?.jsonPrimitive?.contentOrNull?.toInt())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].categoria_id", Matchers.equalTo(
                allVideosExpectedJsonArray[0].jsonObject["categoria_id"]?.jsonPrimitive?.contentOrNull?.toInt())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].titulo", Matchers.equalTo(allVideosExpectedJsonArray[0].jsonObject["titulo"]?.jsonPrimitive?.contentOrNull)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].descricao", Matchers.equalTo(
                allVideosExpectedJsonArray[0].jsonObject["descricao"]?.jsonPrimitive?.contentOrNull)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].url", Matchers.equalTo(allVideosExpectedJsonArray[0].jsonObject["url"]?.jsonPrimitive?.contentOrNull)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].id", Matchers.equalTo(allVideosExpectedJsonArray[1].jsonObject["id"]?.jsonPrimitive?.contentOrNull?.toInt())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].categoria_id", Matchers.equalTo(
                allVideosExpectedJsonArray[1].jsonObject["categoria_id"]?.jsonPrimitive?.contentOrNull?.toInt())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].titulo", Matchers.equalTo(allVideosExpectedJsonArray[1].jsonObject["titulo"]?.jsonPrimitive?.contentOrNull)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].descricao", Matchers.equalTo(
                allVideosExpectedJsonArray[1].jsonObject["descricao"]?.jsonPrimitive?.contentOrNull)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].url", Matchers.equalTo(allVideosExpectedJsonArray[1].jsonObject["url"]?.jsonPrimitive?.contentOrNull)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[2].id", Matchers.equalTo(allVideosExpectedJsonArray[2].jsonObject["id"]?.jsonPrimitive?.contentOrNull?.toInt())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[2].categoria_id", Matchers.equalTo(
                allVideosExpectedJsonArray[2].jsonObject["categoria_id"]?.jsonPrimitive?.contentOrNull?.toInt())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[2].titulo", Matchers.equalTo(allVideosExpectedJsonArray[2].jsonObject["titulo"]?.jsonPrimitive?.contentOrNull)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[2].descricao", Matchers.equalTo(
                allVideosExpectedJsonArray[2].jsonObject["descricao"]?.jsonPrimitive?.contentOrNull)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[2].url", Matchers.equalTo(allVideosExpectedJsonArray[2].jsonObject["url"]?.jsonPrimitive?.contentOrNull)))
    }

    @Test
    fun `should return all videos with category ID 02 when status is 200 ok`() {
        val fileResponseUri = super.getAllVideosWithCategoryId02ResponseFileUri()
        val allVideosWithCategoryId02ExpectedResponse = super.readJsonContentFromFile(fileResponseUri)
        val allVideosWithCategoryId02ExpectedJsonArray = Json.parseToJsonElement(allVideosWithCategoryId02ExpectedResponse).jsonArray

        mockMvc.perform(
            MockMvcRequestBuilders.get(VIDEO_V1_API_PATH)
                .header(HttpHeaders.AUTHORIZATION, super.buildBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id", Matchers.equalTo(
                allVideosWithCategoryId02ExpectedJsonArray[0].jsonObject["id"]?.jsonPrimitive?.contentOrNull?.toInt())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].categoria_id", Matchers.equalTo(
                allVideosWithCategoryId02ExpectedJsonArray[0].jsonObject["categoria_id"]?.jsonPrimitive?.contentOrNull?.toInt())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].titulo", Matchers.equalTo(
                allVideosWithCategoryId02ExpectedJsonArray[0].jsonObject["titulo"]?.jsonPrimitive?.contentOrNull)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].descricao", Matchers.equalTo(
                allVideosWithCategoryId02ExpectedJsonArray[0].jsonObject["descricao"]?.jsonPrimitive?.contentOrNull)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].url", Matchers.equalTo(
                allVideosWithCategoryId02ExpectedJsonArray[0].jsonObject["url"]?.jsonPrimitive?.contentOrNull)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].id", Matchers.equalTo(
                allVideosWithCategoryId02ExpectedJsonArray[1].jsonObject["id"]?.jsonPrimitive?.contentOrNull?.toInt())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].categoria_id", Matchers.equalTo(
                allVideosWithCategoryId02ExpectedJsonArray[1].jsonObject["categoria_id"]?.jsonPrimitive?.contentOrNull?.toInt())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].titulo", Matchers.equalTo(
                allVideosWithCategoryId02ExpectedJsonArray[1].jsonObject["titulo"]?.jsonPrimitive?.contentOrNull)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].descricao", Matchers.equalTo(
                allVideosWithCategoryId02ExpectedJsonArray[1].jsonObject["descricao"]?.jsonPrimitive?.contentOrNull)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].url", Matchers.equalTo(
                allVideosWithCategoryId02ExpectedJsonArray[1].jsonObject["url"]?.jsonPrimitive?.contentOrNull)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[2].id", Matchers.equalTo(
                allVideosWithCategoryId02ExpectedJsonArray[2].jsonObject["id"]?.jsonPrimitive?.contentOrNull?.toInt())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[2].categoria_id", Matchers.equalTo(
                allVideosWithCategoryId02ExpectedJsonArray[2].jsonObject["categoria_id"]?.jsonPrimitive?.contentOrNull?.toInt())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[2].titulo", Matchers.equalTo(
                allVideosWithCategoryId02ExpectedJsonArray[2].jsonObject["titulo"]?.jsonPrimitive?.contentOrNull)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[2].descricao", Matchers.equalTo(
                allVideosWithCategoryId02ExpectedJsonArray[2].jsonObject["descricao"]?.jsonPrimitive?.contentOrNull)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[2].url", Matchers.equalTo(
                allVideosWithCategoryId02ExpectedJsonArray[2].jsonObject["url"]?.jsonPrimitive?.contentOrNull)))
    }

    @Test
    fun `should return an empty list when the category does not contain videos or does not exist`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get(VIDEO_V1_API_PATH.plus("/categoria/5"))
                .header(HttpHeaders.AUTHORIZATION, super.buildBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.empty<List<Video>>()))
    }

    @Test
    fun `should return a video with title containing Semana 01 when the search is successful`() {
        val fileResponseUri = super.getVideoWeek01ResponseFileUri()
        val videoWeek01ExpectedResponse = super.readJsonContentFromFile(fileResponseUri)
        val videoWeek01ExpectedJsonArray = Json.parseToJsonElement(videoWeek01ExpectedResponse).jsonArray

        mockMvc.perform(
            MockMvcRequestBuilders.get(VIDEO_V1_API_PATH.plus("/search")).queryParam("titulo", "Semana 01")
                .header(HttpHeaders.AUTHORIZATION, super.buildBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id", Matchers.equalTo(videoWeek01ExpectedJsonArray[0].jsonObject["id"]?.jsonPrimitive?.contentOrNull?.toInt())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].categoria_id", Matchers.equalTo(
                videoWeek01ExpectedJsonArray[0].jsonObject["categoria_id"]?.jsonPrimitive?.contentOrNull?.toInt())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].titulo", Matchers.equalTo(
                videoWeek01ExpectedJsonArray[0].jsonObject["titulo"]?.jsonPrimitive?.contentOrNull)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].descricao", Matchers.equalTo(
                videoWeek01ExpectedJsonArray[0].jsonObject["descricao"]?.jsonPrimitive?.contentOrNull)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].url", Matchers.equalTo(videoWeek01ExpectedJsonArray[0].jsonObject["url"]?.jsonPrimitive?.contentOrNull)))
    }

    @Test
    fun `should return a video with title containing Semana 02 when the search is successful`() {
        val fileResponseUri = super.getVideoWeek02ResponseFileUri()
        val videoWeek02ExpectedResponse = super.readJsonContentFromFile(fileResponseUri)
        val videoWeek02ExpectedJsonArray = Json.parseToJsonElement(videoWeek02ExpectedResponse).jsonArray

        mockMvc.perform(
            MockMvcRequestBuilders.get(VIDEO_V1_API_PATH.plus("/search")).queryParam("titulo", "Semana 02")
                .header(HttpHeaders.AUTHORIZATION, super.buildBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id", Matchers.equalTo(videoWeek02ExpectedJsonArray[0].jsonObject["id"]?.jsonPrimitive?.contentOrNull?.toInt())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].categoria_id", Matchers.equalTo(
                videoWeek02ExpectedJsonArray[0].jsonObject["categoria_id"]?.jsonPrimitive?.contentOrNull?.toInt())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].titulo", Matchers.equalTo(
                videoWeek02ExpectedJsonArray[0].jsonObject["titulo"]?.jsonPrimitive?.contentOrNull)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].descricao", Matchers.equalTo(
                videoWeek02ExpectedJsonArray[0].jsonObject["descricao"]?.jsonPrimitive?.contentOrNull)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].url", Matchers.equalTo(videoWeek02ExpectedJsonArray[0].jsonObject["url"]?.jsonPrimitive?.contentOrNull)))
    }

    @Test
    fun `should return a video with title containing Semana 03 when the search is successful`() {
        val fileResponseUri = super.getVideoWeek03ResponseFileUri()
        val videoWeek03ExpectedResponse = super.readJsonContentFromFile(fileResponseUri)
        val videoWeek03ExpectedJsonArray = Json.parseToJsonElement(videoWeek03ExpectedResponse).jsonArray

        mockMvc.perform(
            MockMvcRequestBuilders.get(VIDEO_V1_API_PATH.plus("/search")).queryParam("titulo", "Semana 03")
                .header(HttpHeaders.AUTHORIZATION, super.buildBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id", Matchers.equalTo(videoWeek03ExpectedJsonArray[0].jsonObject["id"]?.jsonPrimitive?.contentOrNull?.toInt())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].categoria_id", Matchers.equalTo(
                videoWeek03ExpectedJsonArray[0].jsonObject["categoria_id"]?.jsonPrimitive?.contentOrNull?.toInt())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].titulo", Matchers.equalTo(
                videoWeek03ExpectedJsonArray[0].jsonObject["titulo"]?.jsonPrimitive?.contentOrNull)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].descricao", Matchers.equalTo(
                videoWeek03ExpectedJsonArray[0].jsonObject["descricao"]?.jsonPrimitive?.contentOrNull)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].url", Matchers.equalTo(videoWeek03ExpectedJsonArray[0].jsonObject["url"]?.jsonPrimitive?.contentOrNull)))
    }

    @Test
    fun `should return an empty list when the search does not find any video with the searched title`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get(VIDEO_V1_API_PATH.plus("/search")).queryParam("titulo", "Semana 04")
                .header(HttpHeaders.AUTHORIZATION, super.buildBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.empty<List<Video>>()))
    }

    @Test
    fun `should return video ID 01 when status is 200 ok`() {
        val fileResponseUri = super.getVideoId01ResponseFileUri()
        val videoId01ExpectedResponse = super.readJsonContentFromFile(fileResponseUri)

        mockMvc.perform(
            MockMvcRequestBuilders.get(VIDEO_V1_API_PATH.plus("/1"))
                .header(HttpHeaders.AUTHORIZATION, super.buildBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(videoId01ExpectedResponse))
    }

    @Test
    fun `should return video ID 02 when status is 200 ok`() {
        val fileResponseUri = super.getVideoId02ResponseFileUri()
        val videoId02ExpectedResponse = super.readJsonContentFromFile(fileResponseUri)

        mockMvc.perform(
            MockMvcRequestBuilders.get(VIDEO_V1_API_PATH.plus("/2"))
                .header(HttpHeaders.AUTHORIZATION, super.buildBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(videoId02ExpectedResponse))
    }

    @Test
    fun `should return video ID 03 when status is 200 ok`() {
        val fileResponseUri = super.getVideoId03ResponseFileUri()
        val videoId03ExpectedResponse = super.readJsonContentFromFile(fileResponseUri)

        mockMvc.perform(
            MockMvcRequestBuilders.get(VIDEO_V1_API_PATH.plus("/3"))
                .header(HttpHeaders.AUTHORIZATION, super.buildBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(videoId03ExpectedResponse))
    }

    @Test
    @Transactional
    fun `should save a new video when status is 201 created`() {
        val fileRequestUri = super.getVideoId04RequestFileUri()
        val videoId04ExpectedRequest = super.readJsonContentFromFile(fileRequestUri)

        val fileResponseUri = super.getVideoId04ResponseFileUri()
        val videoId04ExpectedResponse = super.readJsonContentFromFile(fileResponseUri)

        mockMvc.perform(
            MockMvcRequestBuilders.post(VIDEO_V1_API_PATH)
                .header(HttpHeaders.AUTHORIZATION, super.buildBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(videoId04ExpectedRequest)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(videoId04ExpectedResponse))
            .andExpect(MockMvcResultMatchers.header().string("Location", LOCAL_HOST.plus(VIDEO_V1_API_PATH.plus("/4"))))
    }

    @Test
    @Transactional
    fun `should update video ID 03 when status is 200 ok`() {
        val fileRequestUri = super.getVideoId03UpdateRequestFileUri()
        val videoId03ExpectedUpdateRequest = super.readJsonContentFromFile(fileRequestUri)

        val fileResponseUri = super.getVideoId03UpdatedResponseFileUri()
        val videoId03ExpectedUpdatedResponse = super.readJsonContentFromFile(fileResponseUri)

        mockMvc.perform(
            MockMvcRequestBuilders.put(VIDEO_V1_API_PATH.plus("/3"))
                .header(HttpHeaders.AUTHORIZATION, super.buildBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(videoId03ExpectedUpdateRequest)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(videoId03ExpectedUpdatedResponse))
    }

    @Test
    @Transactional
    fun `should delete video ID 03 when status is 204 no content`() {
        mockMvc.perform(
            MockMvcRequestBuilders.delete(VIDEO_V1_API_PATH.plus("/3"))
                .header(HttpHeaders.AUTHORIZATION, super.buildBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNoContent)

        mockMvc.perform(
            MockMvcRequestBuilders.get(VIDEO_V1_API_PATH.plus("/3"))
                .header(HttpHeaders.AUTHORIZATION, super.buildBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(VIDEO_NOT_FOUND_EXCEPTION_MESSAGE))
    }

    @Test
    fun `should return status code 404 when trying to retrieve a video with a non-existent ID`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get(VIDEO_V1_API_PATH.plus("/10"))
                .header(HttpHeaders.AUTHORIZATION, super.buildBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(VIDEO_NOT_FOUND_EXCEPTION_MESSAGE))
    }

    @Test
    fun `should return status code 400 when some video request field is blank`() {
        val fileRequestUri = super.getBlankFieldVideoRequestFileUri()
        val blankFieldExpectedRequest = super.readJsonContentFromFile(fileRequestUri)

        mockMvc.perform(
            MockMvcRequestBuilders.post(VIDEO_V1_API_PATH)
                .header(HttpHeaders.AUTHORIZATION, super.buildBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(blankFieldExpectedRequest)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.ARGUMENT_NOT_VALID_MESSAGE))
    }

    @Test
    fun `should return status code 400 when video url request field is invalid`() {
        val fileRequestUri = super.getInvalidUrlFieldVideoRequestFileUri()
        val invalidUrlFieldExpectedVideoRequest = super.readJsonContentFromFile(fileRequestUri)

        mockMvc.perform(
            MockMvcRequestBuilders.post(VIDEO_V1_API_PATH)
                .header(HttpHeaders.AUTHORIZATION, super.buildBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(invalidUrlFieldExpectedVideoRequest)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.ARGUMENT_NOT_VALID_MESSAGE))
    }

    @Test
    fun `should return status code 400 when trying to save a video with non-existent or invalid json`() {
        mockMvc.perform(
            MockMvcRequestBuilders.post(VIDEO_V1_API_PATH)
                .header(HttpHeaders.AUTHORIZATION, super.buildBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{}")
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.HTTP_MESSAGE_NOT_READABLE))
    }

    @Test
    fun `should return status code 404 when trying to update a video with a non-existent ID`() {
        val fileRequestUri = super.getVideoId03UpdateRequestFileUri()
        val videoId03ExpectedUpdatedResponse = super.readJsonContentFromFile(fileRequestUri)

        mockMvc.perform(
            MockMvcRequestBuilders.put(VIDEO_V1_API_PATH.plus("/10"))
                .header(HttpHeaders.AUTHORIZATION, super.buildBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(videoId03ExpectedUpdatedResponse)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(VIDEO_NOT_FOUND_EXCEPTION_MESSAGE))
    }

    @Test
    fun `should return status code 404 when trying to delete a video with a non-existent ID`() {
        mockMvc.perform(
            MockMvcRequestBuilders.delete(VIDEO_V1_API_PATH.plus("/10"))
                .header(HttpHeaders.AUTHORIZATION, super.buildBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(VIDEO_NOT_FOUND_EXCEPTION_MESSAGE))
    }

    @Test
    fun `should return status code 500 when data base connection fail`() {
        `when`(videoRepository.findById(anyLong()))
            .thenThrow(PersistenceException("Database transaction failed!"))

        mockMvc.perform(
            MockMvcRequestBuilders.get(VIDEO_V1_API_PATH.plus("/9"))
                .header(HttpHeaders.AUTHORIZATION, super.buildBasicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isInternalServerError)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.INTERNAL_SERVER_ERROR_MESSAGE))
    }
}