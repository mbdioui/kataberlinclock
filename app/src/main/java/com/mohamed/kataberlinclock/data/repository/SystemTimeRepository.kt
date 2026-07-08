package com.mohamed.kataberlinclock.data.repository

import com.mohamed.kataberlinclock.domain.model.Clock
import com.mohamed.kataberlinclock.domain.model.Time
import com.mohamed.kataberlinclock.domain.repository.TimeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.milliseconds

internal class SystemTimeRepository(
    private val clock: Clock
) : TimeRepository {

    override fun getLiveTime(): Flow<Time> = flow {
        while (true) {
            emit(clock.getCurrentTime())
            delay(1000.milliseconds)
        }
    }
}