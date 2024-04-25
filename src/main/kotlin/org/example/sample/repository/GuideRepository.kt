package org.example.sample.repository

import org.example.sample.domain.Guide
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GuideRepository : CoroutineCrudRepository<Guide, Long> {
}