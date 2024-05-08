package org.example.sample.service

import kotlinx.coroutines.flow.Flow
import org.example.sample.core.NotFoundException
import org.example.sample.domain.Guide
import org.example.sample.domain.GuideType
import org.example.sample.payload.GuideCreatePayload
import org.example.sample.payload.GuideUpdatePayload
import org.example.sample.repository.GuideRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class GuideService(
    private val guideRepository: GuideRepository
) {
    suspend fun getAllGuides(): Flow<Guide> = guideRepository.findAll()

    suspend fun getGuide(id: Long): Guide = guideRepository.findById(id) ?: throw NotFoundException()

    suspend fun getGuideByType(type: GuideType): Flow<Guide> = guideRepository.findByType(type)

    @Transactional
    suspend fun saveGuide(payload: GuideCreatePayload): Guide {
        return guideRepository.save(
            Guide(
                title = payload.title,
                author = payload.author,
                type = payload.type
            )
        )
    }

    @Transactional
    suspend fun updateGuide(payload: GuideUpdatePayload): Guide {
        val guide = guideRepository.findById(payload.id!!) ?: throw NotFoundException()
        return guideRepository.save(
            guide.copy(
                title = payload.title,
                author = payload.author,
                type = payload.type
            )
        )
    }

    @Transactional
    suspend fun disableGuide(id: Long): Long {
        val guide = guideRepository.findById(id) ?: throw NotFoundException()
        return guideRepository.save(
            guide.copy(
                isEnable = !guide.isEnable
            )
        ).id!!
    }

}