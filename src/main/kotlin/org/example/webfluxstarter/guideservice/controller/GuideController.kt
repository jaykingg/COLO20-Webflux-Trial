package org.example.webfluxstarter.guideservice.controller

import jakarta.validation.constraints.Min
import org.example.webfluxstarter.core.PagedView
import org.example.webfluxstarter.core.toPagedView
import org.example.webfluxstarter.guideservice.domain.Guide
import org.example.webfluxstarter.guideservice.service.GuideService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/guide")
class GuideController(
    private val guideService: GuideService
) {

    @GetMapping
    suspend fun getAllGuides(
        @RequestParam(value = "page", defaultValue = "0") @Min(0) page: Int,
        @RequestParam(value = "size", defaultValue = "10") @Min(1) size: Int,
        @RequestParam(value = "sort", defaultValue = "name") sort: String
    ): PagedView<Guide> {
        val pageable: Pageable = PageRequest.of(page, size, Sort.by(sort))
        return guideService.findAllGuide(pageable).toPagedView()
    }

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