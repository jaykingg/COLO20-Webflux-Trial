package org.example.sample.core.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.support.WebExchangeBindException
import org.springframework.web.server.ResponseStatusException
import java.time.Instant

class ErrorResponse private constructor(
    val timestamp: Long = Instant.now().toEpochMilli(),

    /**
     * Http Status Code
     */
    val status: Int,

    /**
     * Http Status Reason
     */
    val error: String,

    /**
     * Error Detail Code
     */
    val detailCode: String? = null,

    /**
     * Error 내용
     */
    val message: String? = null,

    /**
     * Input field 에러 리스트
     */
    val fields: List<SimpleFieldError> = emptyList()
) {
    companion object {
        /**
         * 특저어 Exception Translator
         */
        fun from(exception: WebExchangeBindException) = ErrorResponse(
            status = exception.statusCode.value(),
            error = HttpStatus.valueOf(exception.statusCode.value()).reasonPhrase,
            detailCode = exception.detailMessageCode,
            message = exception.message,
            fields = exception.bindingResult.fieldErrors.map { it.toCustomFieldError() }
        )

        fun from(exception: AbstractErrorCodeException) = ErrorResponse(
            status = exception.statusCode.value(),
            error = HttpStatus.valueOf(exception.statusCode.value()).reasonPhrase,
            detailCode = exception.detailMessageCode,
            message = exception.message
        )

        fun from(exception: ResponseStatusException) = ErrorResponse(
            status = exception.statusCode.value(),
            error = exception.reason ?: "잘못된 요청입니다.",
            detailCode = exception.detailMessageCode,
            message = exception.message
        )

        /**
         * 일반적인 Exception 정의
         */
        fun ofBadRequest() = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.reasonPhrase,
            message = "잘못된 요청입니다"
        )

        fun ofInternal() = ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase
        )
    }
}