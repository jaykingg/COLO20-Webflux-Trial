package org.example.sample.service

import org.example.sample.core.ApiResponse
import org.example.sample.domain.Guide
import org.example.sample.payload.CreateGuidePayload
import org.example.sample.payload.UpdateGuidePayload
import org.example.sample.repository.GuideRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono


@Service
class GuideService(
    private val guideRepository: GuideRepository
) {
    fun getAllGuides(): Mono<ApiResponse<List<Guide>>> {
        return guideRepository.findAll()
            .collectList()
            .map { guide ->
                ApiResponse(
                    code = HttpStatus.OK.value(),
                    message = "Guide 가 정상적으로 모두 조회되었습니다.",
                    data = guide
                )
            }
            .onErrorResume { error ->
                Mono.just(
                    ApiResponse(
                        code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        message = error.message,
                        data = null
                    )
                )

            }
    }

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
    fun updateGuide(payload: UpdateGuidePayload): Mono<ApiResponse<Guide>> {
        return guideRepository.findById(payload.id!!)
            .map {
                val updatedGuide = it.copy(
                    title = payload.title,
                    author = payload.title,
                    type = payload.type
                )
                ApiResponse(
                    code = HttpStatus.OK.value(),
                    message = "Guide 가 정상적으로 수정되었습니다.",
                    data = updatedGuide
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