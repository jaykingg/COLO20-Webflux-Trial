package org.example.sample.domain

import com.fasterxml.jackson.annotation.JsonProperty

enum class GuideType(val label: String) {
    @field: JsonProperty("all")
    ALL("전체 가이드"),

    @field: JsonProperty("division")
    DIVISION("부문 가이드"),

    @field: JsonProperty("team")
    TEAM("팀 가이드")
}