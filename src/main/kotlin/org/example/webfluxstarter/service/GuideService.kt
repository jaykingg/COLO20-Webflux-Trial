package org.example.webfluxstarter.service

import org.example.webfluxstarter.domain.Guide
import org.example.webfluxstarter.repository.GuideRepository
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