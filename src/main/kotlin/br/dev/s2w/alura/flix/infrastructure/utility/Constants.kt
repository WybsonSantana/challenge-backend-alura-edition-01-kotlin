package br.dev.s2w.alura.flix.infrastructure.utility

object Constants {

    // API Mapping
    const val CATEGORIA_API_V1_MAPPING = "/api/v1/categorias"
    const val VIDEO_API_V1_MAPPING = "/api/v1/videos"

    // Error Messages
    const val CATEGORIA_NOT_FOUND_EXCEPTION_MESSAGE = "Ops! Category not found!"
    const val VIDEO_NOT_FOUND_EXCEPTION_MESSAGE = "Ops! Video not found!"
    const val ARGUMENT_NOT_VALID_MESSAGE = "Ops! Some fields were not validated!"
    const val HTTP_MESSAGE_NOT_READABLE = "Ops! Check the fields of your request!"
    const val INTERNAL_SERVER_ERROR_MESSAGE = "Ops! Something went wrong"
}