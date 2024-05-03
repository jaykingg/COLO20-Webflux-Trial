package org.example.sample.view

import com.fasterxml.jackson.annotation.JsonFormat
import org.example.sample.domain.GuideType
import java.time.Instant

data class GuideView(
    val title: String,

    val author: String,

    val type: GuideType,

    val isEnable: Boolean = true,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    val createAt: Instant? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    val updateAt: Instant? = null
)