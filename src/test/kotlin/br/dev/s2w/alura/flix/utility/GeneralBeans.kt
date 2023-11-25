package br.dev.s2w.alura.flix.utility

import br.dev.s2w.alura.flix.gateway.repository.CategoriaRepository
import org.springframework.boot.test.mock.mockito.MockBean
import java.nio.file.Files
import java.nio.file.Paths

open class GeneralBeans {

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


    fun getAllVideosCategoryId02ResponseFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosResponse/all-videos-category-id-02-response.json"

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

    fun getInvalidUrlVideoRequestFileUri() =
        "src/test/kotlin/br/dev/s2w/alura/flix/utility/expectedVideosRequest/invalid-url-video-request.json"

    fun readJsonContentFromFile(uri: String): String {
        val filePath = Paths.get(uri)
        val fileContent = String(Files.readAllBytes(filePath))
        return fileContent
    }
}