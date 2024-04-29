package org.example.sample.service

import org.example.sample.domain.Guide
import org.example.sample.payload.CreateGuidePayload
import org.example.sample.payload.UpdateGuidePayload
import org.example.sample.repository.GuideRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
class GuideService(
    private val guideRepository: GuideRepository
) {
    fun getAllGuides(): Flux<Guide> = guideRepository.findAll()

    fun getGuide(id: Long): Mono<Guide> = guideRepository.findById(id)

    @Transactional
    fun saveGuide(payload: CreateGuidePayload): Mono<Guide> {
        return guideRepository.save(
            Guide(
                title = payload.title,
                author = payload.author,
                type = payload.type
            )
        )
    }

    @Transactional
    fun updateGuide(payload: UpdateGuidePayload): Mono<Guide> {
        return guideRepository.findById(payload.id).flatMap {
            guideRepository.save(
                it.copy(
                    title = payload.title,
                    author = payload.author,
                    type = payload.type
                )
            )
        }
    }

    @Transactional
    fun disableGuide(id: Long): Mono<Long> {
        return guideRepository.findById(id).flatMap {
            guideRepository.save(
                it.copy(
                    isEnable = !it.isEnable

                )
            )
        }.mapNotNull {
            it.id
        }
    }
}