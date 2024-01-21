package br.dev.s2w.alura.flix.utility

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Base64

open class GeneralBeans {

    private fun getUsername() = "fulanodetal@mail.com"

    private fun getPassword() = "123456"

    fun buildBasicAuthHeader(): String {
        val getBase64CredentialsForAuthentication = Base64.getEncoder()
            .encodeToString("${getUsername()}:${getPassword()}".toByteArray())

        return "Basic".plus(" ").plus(getBase64CredentialsForAuthentication)
    }

    fun getAllCategoriesResponseFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedCategoriesResponse/all-categories-response.json"

    fun getFreeCategoryResponseFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedCategoriesResponse/free-category-response.json"

    fun getBackEndCategoryResponseFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedCategoriesResponse/back-end-category-response.json"

    fun getDataBaseCategoryResponseFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedCategoriesResponse/data-base-category-response.json"

    fun getCloudCategoryRequestFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedCategoriesRequest/cloud-category-request.json"

    fun getCloudCategoryResponseFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedCategoriesResponse/cloud-category-response.json"

    fun getDataBaseCategoryUpdateRequestFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedCategoriesRequest/data-base-category-update-request.json"

    fun getDataBaseCategoryUpdatedResponseFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedCategoriesResponse/data-base-category-updated-response.json"

    fun getBlankFieldCategoryRequestFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedCategoriesRequest/blank-field-category-request.json"

    fun getAllVideosResponseFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosResponse/all-videos-response.json"

    fun getAllVideosWithCategoryId02ResponseFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosResponse/all-videos-with-category-id-02-response.json"

    fun getAllVideosWeekQueryResultFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosResponse/video-all-week-query-result.json"

    fun getVideoWeek01QueryResultFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosResponse/video-week-01-query-result.json"

    fun getVideoWeek02QueryResultFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosResponse/video-week-02-query-result.json"

    fun getVideoWeek03QueryResultFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosResponse/video-week-03-query-result.json"

    fun getVideoId01QueryResultFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosResponse/video-id-01-query-result.json"

    fun getVideoId02QueryResultFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosResponse/video-id-02-query-result.json"

    fun getVideoId03QueryResultFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosResponse/video-id-03-query-result.json"

    fun getVideoId03UpdatedQueryResultFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosResponse/video-id-03-updated-query-result.json"

    fun getVideoId04QueryResultFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosResponse/video-id-04-query-result.json"

    fun getVideoWeek01ResponseFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosResponse/video-week-01-response.json"

    fun getVideoWeek02ResponseFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosResponse/video-week-02-response.json"

    fun getVideoWeek03ResponseFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosResponse/video-week-03-response.json"

    fun getVideoId01ResponseFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosResponse/video-id-01-response.json"

    fun getVideoId02ResponseFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosResponse/video-id-02-response.json"

    fun getVideoId03ResponseFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosResponse/video-id-03-response.json"

    fun getVideoId04RequestFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosRequest/video-id-04-request.json"

    fun getVideoId04ResponseFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosResponse/video-id-04-response.json"

    fun getVideoId03UpdateRequestFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosRequest/video-id-03-update-request.json"

    fun getVideoId03UpdatedResponseFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosResponse/video-id-03-updated-response.json"

    fun getBlankFieldVideoRequestFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosRequest/blank-field-video-request.json"

    fun getInvalidUrlFieldVideoRequestFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosRequest/invalid-url-field-video-request.json"

    fun readJsonContentFromFile(uri: String): String {
        val filePath = Paths.get(uri)
        return String(Files.readAllBytes(filePath))
    }

    inline fun <reified T> buildTypeReference(): TypeReference<T> {
        return object : TypeReference<T>() {}
    }

    inline fun <reified T> convertJsonContentStringToObject(jsonContent: String, typeReference: TypeReference<T>): T {
        val objectMapper = ObjectMapper()
        return objectMapper.readValue(jsonContent, typeReference)
    }
}