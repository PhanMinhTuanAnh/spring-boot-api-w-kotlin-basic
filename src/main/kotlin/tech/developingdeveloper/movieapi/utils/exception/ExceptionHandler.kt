package tech.developingdeveloper.movieapi.utils.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.IllegalArgumentException

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun generalExceptionHandler(exception: Exception): ResponseEntity<ApiError> {
        val error = ApiError(exception.message)
        return ResponseEntity(error, error.status)
    }

    @ExceptionHandler(MovieException::class)
    fun movieExceptionHandler(exception: Exception): ResponseEntity<ApiError> {
        val error = ApiError(exception.message)
        return ResponseEntity(error, error.status)
    }
}