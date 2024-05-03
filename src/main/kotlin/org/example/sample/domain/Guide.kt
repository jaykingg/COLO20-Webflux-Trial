package org.example.sample.domain

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import org.example.sample.view.GuideView
import org.hibernate.validator.constraints.Length
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table
data class Guide(

    @field: Schema(description = "Guide Id")
    @field: Id
    val id: Long? = null,

    @field: Schema(description = "Guide 제목")
    @field: NotBlank
    val title: String,

    @field: Schema(description = "Guide 작성자")
    @field: NotBlank
    @field: Length(max = 20)
    val author: String,

    @field: Schema(description = "Guide 타입")
    @field: NotBlank
    val type: GuideType,

    @field: Schema(description = "Guide 활성/비활성 상태")
    @field: NotBlank
    val isEnable: Boolean = true,

    @field: Schema(description = "생성일자 - UTC")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    val createAt: Instant? = null,

    @field: Schema(description = "수정일자 - UTC")
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    val updateAt: Instant? = null

)

fun Guide.toView() = GuideView(
    title = title,
    author = author,
    type = type,
    isEnable = isEnable,
    createAt = createAt,
    updateAt = updateAt
)