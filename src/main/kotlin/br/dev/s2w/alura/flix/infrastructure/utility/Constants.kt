package br.dev.s2w.alura.flix.infrastructure.utility

object Constants {

    // API Mapping
    const val CATEGORIA_V1_API_PATH = "/api/v1/categorias"
    const val VIDEO_V1_API_PATH = "/api/v1/videos"

    // Error Messages
    const val CATEGORIA_NOT_FOUND_EXCEPTION_MESSAGE = "Ops! Category not found!"
    const val CATEGORIA_IN_USE_EXCEPTION_MESSAGE = "Ops! This category cannot be removed because it's in use!"
    const val VIDEO_NOT_FOUND_EXCEPTION_MESSAGE = "Ops! Video not found!"
    const val ARGUMENT_NOT_VALID_MESSAGE = "Ops! Some fields were not validated!"
    const val HTTP_MESSAGE_NOT_READABLE = "Ops! Check the fields of your request!"
    const val INTERNAL_SERVER_ERROR_MESSAGE = "Ops! Something went wrong"
}