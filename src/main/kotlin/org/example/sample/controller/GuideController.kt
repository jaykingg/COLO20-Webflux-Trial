package org.example.sample.controller

import org.example.sample.domain.Guide
import org.example.sample.payload.InsertGuidePayload
import org.example.sample.payload.UpdateGuidePayload
import org.example.sample.service.GuideService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/guide")
class GuideController(
    private val guideService: GuideService
) {

    @GetMapping
    fun getAllGuides(
    ): Flux<Guide> = guideService.getAllGuides()


    @GetMapping("/{id}")
    fun getGuide(
        @PathVariable id: Long
    ): Mono<Guide> = guideService.getGuide(id)

    @PostMapping
    fun saveGuide(
        @Validated @RequestBody payload: InsertGuidePayload
    ): Mono<Guide> = guideService.saveGuide(payload)

    @PatchMapping
    fun updateGuide(
        @Validated @RequestBody payload: UpdateGuidePayload
    ): Mono<Guide> = guideService.updateGuide(payload)

    @PatchMapping("/{id}")
    fun disableGuide(@PathVariable id: Long): Mono<Long> = guideService.disableGuide(id)

}