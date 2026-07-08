package com.mohamed.kataberlinclock.data.repository

import com.mohamed.kataberlinclock.domain.model.Clock
import com.mohamed.kataberlinclock.domain.model.Time
import app.cash.turbine.test
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SystemTimeRepositoryTest {

    @Test
    fun `getLiveTime should periodically emit current time from clock`() = runTest {
        val expectedTime = Time(14, 30, 15)
        val fakeClock = object : Clock {
            override fun getCurrentTime(): Time = expectedTime
        }
        val repository = SystemTimeRepository(fakeClock)

        repository.getLiveTime().take(2).test {
            assertEquals(expectedTime, awaitItem())
            assertEquals(expectedTime, awaitItem())
            awaitComplete()
        }
    }
}