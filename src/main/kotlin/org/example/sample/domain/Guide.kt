package org.example.sample.domain

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table
@EnableR2dbcAuditing
data class Guide(

    @field: Id
    val id: Long? = null,

    @field: NotBlank
    val title: String,

    @field: NotBlank
    @field: Length(max = 20)
    val author: String,

    @field: NotBlank
    val type: GuideType,

    @field: NotBlank
    val enable: Boolean = true,

    @field: CreatedDate
    val createAt: Instant? = null,

    @field: LastModifiedDate
    val updateAt: Instant? = null


)