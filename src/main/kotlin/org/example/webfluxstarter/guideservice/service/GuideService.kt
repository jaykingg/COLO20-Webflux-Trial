package org.example.webfluxstarter.guideservice.service

import org.example.webfluxstarter.guideservice.domain.Guide
import org.example.webfluxstarter.guideservice.repository.GuideRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class GuideService(
    private val guideRepository: GuideRepository
) {
    suspend fun findAllGuide(pageable: Pageable): Page<Guide> {
        return guideRepository.findAll(pageable)
    }

}