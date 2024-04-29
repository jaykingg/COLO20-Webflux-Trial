package org.example.sample.core

data class ApiResponse<T>(
    val code: Int,
    val message: String?,
    val data: T?
)