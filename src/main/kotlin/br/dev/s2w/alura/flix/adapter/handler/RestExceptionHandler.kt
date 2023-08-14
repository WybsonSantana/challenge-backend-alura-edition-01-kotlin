package br.dev.s2w.alura.flix.adapter.handler

import br.dev.s2w.alura.flix.adapter.controller.response.ErrorFieldResponse
import br.dev.s2w.alura.flix.adapter.controller.response.ErrorResponse
import br.dev.s2w.alura.flix.adapter.controller.response.ValidationError
import br.dev.s2w.alura.flix.domain.exception.VideoNotFoundException
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.ARGUMENT_NOT_VALID_MESSAGE
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.HTTP_MESSAGE_NOT_READABLE
import br.dev.s2w.alura.flix.infrastructure.utility.Constants.INTERNAL_SERVER_ERROR_MESSAGE
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(VideoNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleVideoNotFoundException(
        exception: VideoNotFoundException,
        request: HttpServletRequest
    ): ErrorResponse {
        return ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message!!,
            path = request.servletPath
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValidException(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ErrorFieldResponse {
        val fieldErrorMap = exception.bindingResult.fieldErrors.associate {
            it.field to it.defaultMessage
        }

        return ErrorFieldResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = ARGUMENT_NOT_VALID_MESSAGE,
            validationError = ValidationError(fieldErrorMap),
            path = request.servletPath
        )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleHttpMessageNotReadableException(
        request: HttpServletRequest
    ): ErrorResponse {
        return ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = HTTP_MESSAGE_NOT_READABLE,
            path = request.servletPath
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleInternalServerError(
        request: HttpServletRequest
    ): ErrorResponse {
        return ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = INTERNAL_SERVER_ERROR_MESSAGE,
            path = request.servletPath
        )
    }

}