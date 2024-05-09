package org.example.sample.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.sample.domain.Guide
import org.example.sample.domain.GuideType
import org.example.sample.domain.toView
import org.example.sample.payload.GuideCreatePayload
import org.example.sample.payload.GuideUpdatePayload
import org.example.sample.service.GuideService
import org.example.sample.view.GuideView
import org.springframework.web.bind.annotation.*

@Tag(name = "Guide API", description = "Guide API for Sample")
@RestController
@RequestMapping("/api/guide")
class GuideController(
    private val guideService: GuideService
) {

    @Operation(summary = "Guide 전체 조회")
    @GetMapping
    suspend fun getAllGuides(): Flow<GuideView> = guideService.getAllGuides().map { it.toView() }

    @Operation(summary = "Guide 단일 조회")
    @GetMapping("/{id}")
    suspend fun getGuide(
        @PathVariable id: Long
    ): Guide = guideService.getGuide(id)

    @Operation(summary = "Type 으로 Guide 조회")
    @GetMapping("/type")
    suspend fun getGuideByType(
        @RequestParam type: GuideType
    ): Flow<Guide> = guideService.getGuideByType(type)

    @Operation(summary = "Guide 단일 저장")
    @PostMapping
    suspend fun saveGuide(
        @Valid @RequestBody payload: GuideCreatePayload
    ): Guide = guideService.saveGuide(payload)


    @Operation(summary = "Guide 수정")
    @PutMapping
    suspend fun updateGuide(
        @Valid @RequestBody payload: GuideUpdatePayload
    ): Guide = guideService.updateGuide(payload)

    @Operation(summary = "Guide 상태 변경")
    @PutMapping("/{id}")
    suspend fun disableGuide(@PathVariable id: Long): Long = guideService.disableGuide(id)

    @Operation(summary = "Guide Custom Error 테스트")
    @GetMapping("/test")
    suspend fun customGuideErrorTest(@RequestParam word: String): String = guideService.customGuideErrorTest(word)


}