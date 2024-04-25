package org.example.sample.repository

import org.example.sample.domain.Guide
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

@Repository
interface GuideRepository : R2dbcRepository<Guide, Long> {
}