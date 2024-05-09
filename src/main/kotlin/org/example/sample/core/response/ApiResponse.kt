package org.example.sample.core.response

data class ApiResponse<T>(
    val code: Int,
    val message: String?,
    val data: T?
)