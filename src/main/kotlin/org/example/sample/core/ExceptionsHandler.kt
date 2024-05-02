package org.example.sample.core

import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ServerWebExchange

@RestControllerAdvice
@Order(-2)
class ExceptionsHandler {
    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequest(ex: BadRequestException, exchange: ServerWebExchange): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(
            code = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.reasonPhrase,
            message = "잘못된 요청입니다."
        )
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(ex: NotFoundException, exchange: ServerWebExchange): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(
            code = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.reasonPhrase,
            message = ex.message
        )
        return ResponseEntity(response, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(InternalServerException::class)
    fun handleInternalError(ex: InternalServerException, exchange: ServerWebExchange): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(
            code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
            message = ex.message
        )
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}