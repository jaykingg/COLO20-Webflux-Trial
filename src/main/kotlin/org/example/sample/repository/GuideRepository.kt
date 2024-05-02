package org.example.sample.repository

import kotlinx.coroutines.flow.Flow
import org.example.sample.domain.Guide
import org.example.sample.domain.GuideType
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface GuideRepository : CoroutineCrudRepository<Guide, Long> {
    @Query("SELECT * FROM Guide WHERE type = :type")
    fun findByType(type: GuideType): Flow<Guide>
}