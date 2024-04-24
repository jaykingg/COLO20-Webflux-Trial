package org.example.webfluxstarter.guideservice.repository

import org.example.webfluxstarter.guideservice.domain.Guide
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface GuideRepository : CoroutineCrudRepository<Guide, Long> {
    suspend fun findAll(pageable: Pageable): Page<Guide>
}