package org.example.sample.repository

import org.example.sample.domain.Guide
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface GuideRepository : R2dbcRepository<Guide, Long> {
}