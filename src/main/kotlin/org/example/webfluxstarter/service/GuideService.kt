package org.example.webfluxstarter.service

import kotlinx.coroutines.flow.Flow
import org.example.webfluxstarter.domain.Guide
import org.example.webfluxstarter.repository.GuideRepository
import org.springframework.stereotype.Service

@Service
class GuideService(
    private val guideRepository: GuideRepository
) {
    suspend fun findAllGuide(): Flow<Guide> {
        return guideRepository.findAll()
    }

}