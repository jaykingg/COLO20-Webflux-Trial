package org.example.sample.service

import kotlinx.coroutines.flow.Flow
import org.example.sample.domain.Guide
import org.example.sample.repository.GuideRepository
import org.springframework.stereotype.Service

@Service
class GuideService(
    private val guideRepository: GuideRepository
) {
    suspend fun findAllGuide(): Flow<Guide> {
        return guideRepository.findAll()
    }

}