package org.example.sample.payload

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.example.sample.domain.GuideType
import org.hibernate.validator.constraints.Length

data class CreateGuidePayload(
    @field: Schema(description = "Guide 제목")
    @field: NotBlank
    val title: String,

    @field: Schema(description = "Guide 작성자")
    @field: NotBlank
    @field: Length(max = 20)
    val author: String,

    @field: Schema(description = "Guide 타입")
    @field: Valid
    val type: GuideType,

    @field: Schema(description = "Guide 활성/비활성 상태")
    val isEnable: Boolean? = true
)