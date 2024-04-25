package org.example.webfluxstarter.repository

import org.example.webfluxstarter.domain.Guide
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GuideRepository : CoroutineCrudRepository<Guide, Long> {
}