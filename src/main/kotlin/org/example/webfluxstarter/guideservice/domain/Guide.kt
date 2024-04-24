package org.example.webfluxstarter.guideservice.domain

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant


data class Guide(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

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