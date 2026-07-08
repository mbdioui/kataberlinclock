package com.mohamed.kataberlinclock.domain.repository

import com.mohamed.kataberlinclock.domain.model.Time
import kotlinx.coroutines.flow.Flow

internal interface TimeRepository {
    fun getLiveTime(): Flow<Time>
}