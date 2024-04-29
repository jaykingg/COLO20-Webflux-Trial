package org.example.sample.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.example.sample.domain.Guide
import org.example.sample.payload.CreateGuidePayload
import org.example.sample.payload.UpdateGuidePayload
import org.example.sample.service.GuideService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Tag(name = "Guide API", description = "Guide API for Sample")
@RestController
@RequestMapping("/api/guide")
class GuideController(
    private val guideService: GuideService
) {

    @Operation(summary = "Guide 전체 가져오기")
    @GetMapping
    fun getAllGuides(
    ): Flux<Guide> = guideService.getAllGuides()

    @Operation(summary = "Guide 단일 건 가져오기")
    @GetMapping("/{id}")
    fun getGuide(
        @PathVariable id: Long
    ): Mono<Guide> = guideService.getGuide(id)

    @Operation(summary = "Guide 저장하기")
    @PostMapping
    fun saveGuide(
        @Validated @RequestBody payload: CreateGuidePayload
    ): Mono<Guide> = guideService.saveGuide(payload)

    @Operation(summary = "Guide 수정하기")
    @PatchMapping
    fun updateGuide(
        @Validated @RequestBody payload: UpdateGuidePayload
    ): Mono<Guide> = guideService.updateGuide(payload)

    @Operation(summary = "Guide 상태 변경하기")
    @PatchMapping("/{id}")
    fun disableGuide(@PathVariable id: Long): Mono<Long> = guideService.disableGuide(id)

}