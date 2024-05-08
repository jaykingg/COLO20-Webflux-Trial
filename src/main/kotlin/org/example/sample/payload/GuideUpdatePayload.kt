package org.example.sample.payload

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.example.sample.domain.GuideType
import org.hibernate.validator.constraints.Length

data class GuideUpdatePayload(
    @field: Schema(description = "Guide Id")
    @field: NotNull
    val id: Long?,

    @field: Schema(description = "Guide 제목")
    @field: NotBlank
    @field: Length(max = 20)
    val title: String,

    @field: Schema(description = "Guide 작성자")
    @field: NotBlank
    @field: Length(max = 20)
    val author: String,

    @field: Schema(description = "Guide 타입")
    @field: Valid
    val type: GuideType,
)