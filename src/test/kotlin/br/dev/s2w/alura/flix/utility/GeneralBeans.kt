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

    fun readJsonContentFromFile(uri: String): String {
        val filePath = Paths.get(uri)
        val fileContent = String(Files.readAllBytes(filePath))
        return fileContent
    }
}