package org.example.sample.service

import org.example.sample.domain.Guide
import org.example.sample.repository.GuideRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class GuideService(
    private val guideRepository: GuideRepository
) {
    suspend fun findAllGuide(): Flux<Guide> {
        return guideRepository.findAll()
    }

}