package org.example.webfluxstarter.guideservice.repository

import org.example.webfluxstarter.guideservice.domain.Guide
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface GuideRepository : CoroutineCrudRepository<Guide, Long> {
}