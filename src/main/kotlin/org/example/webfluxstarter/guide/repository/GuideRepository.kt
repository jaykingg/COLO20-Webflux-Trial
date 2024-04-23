package org.example.webfluxstarter.guide.repository

import org.example.webfluxstarter.guide.domain.Guide
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface GuideRepository : CoroutineCrudRepository<Guide, Long> {
}