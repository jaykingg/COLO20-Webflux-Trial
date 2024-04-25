package org.example.sample.controller

import kotlinx.coroutines.flow.Flow
import org.example.sample.domain.Guide
import org.example.sample.service.GuideService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/guide")
class GuideController(
    private val guideService: GuideService
) {

    @GetMapping
    suspend fun getAllGuides(
    ): Flow<Guide> = guideService.findAllGuide()


    @GetMapping("/{id}")
    suspend fun getGuide(
        @PathVariable id: Long
    ) {

    }

    @PostMapping
    suspend fun saveGuide() {

    }

    @PatchMapping
    suspend fun updateGuide() {

    }

    @DeleteMapping
    suspend fun disableGuide() {

    }

}