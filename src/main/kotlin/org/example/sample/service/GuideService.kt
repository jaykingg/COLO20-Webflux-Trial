package org.example.sample.service

import org.example.sample.domain.Guide
import org.example.sample.payload.CreateGuidePayload
import org.example.sample.payload.UpdateGuidePayload
import org.example.sample.repository.GuideRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
class GuideService(
    private val guideRepository: GuideRepository
) {
    fun getAllGuides(): Flux<Guide> = guideRepository.findAll()

    fun getGuide(id: Long): Mono<Guide> = guideRepository.findById(id)

    fun saveGuide(payload: CreateGuidePayload): Mono<Guide> {
        return guideRepository.save(
            Guide(
                title = payload.title,
                author = payload.author,
                type = payload.type
            )
        )
    }

    /**
     * TODO mapNotNull 제거하는 방향으로
     * TODO copy 하여 save 시 데이터가 1개 더 늘어나는 현상있음
     */
    fun updateGuide(payload: UpdateGuidePayload): Mono<Guide> {
        guideRepository.findById(payload.id).mapNotNull {
            return@mapNotNull guideRepository.save(
                it.copy(
                    id = it.id,
                    title = payload.title,
                    author = payload.author,
                    type = payload.type
                )
            )
        }

        return Mono.empty()
    }

    /**
     * TODO Update 와 같은 현상
     */
    fun disableGuide(id: Long): Mono<Long> {
        return guideRepository.findById(id).mapNotNull {
            it.copy(
                enable = false
            ).id
        }
    }


}