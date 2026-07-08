package com.mohamed.kataberlinclock.domain.usecase

import com.mohamed.kataberlinclock.domain.model.Lamp
import com.mohamed.kataberlinclock.domain.model.Time
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ConvertToBerlinClockUseCaseTest {

    private val useCase = ConvertToBerlinClockUseCase()

    @Test
    fun `midnight 00-00-00 should result in seconds lamp on and all row lamps off`() {
        val midnight = Time(hour = 0, minute = 0, second = 0)
        val result = useCase(midnight)

        assertEquals(Lamp.YELLOW, result.secondsLamp)
        assertEquals(List(4) { Lamp.OFF }, result.fiveHoursRow)
        assertEquals(List(4) { Lamp.OFF }, result.oneHourRow)
        assertEquals(List(11) { Lamp.OFF }, result.fiveMinutesRow)
        assertEquals(List(4) { Lamp.OFF }, result.oneMinuteRow)
    }

    @Test
    fun `odd seconds should turn off the single top seconds lamp`() {
        val time = Time(hour = 0, minute = 0, second = 1)
        val result = useCase(time)
        assertEquals(Lamp.OFF, result.secondsLamp)
    }

    @Test
    fun `midday 12-34-05 should map correct counts and alternating quarter colors`() {
        val time = Time(hour = 12, minute = 34, second = 5)
        val result = useCase(time)

        // 12 hours / 5 = 2 lamps on the 5-hour row
        assertEquals(listOf(Lamp.RED, Lamp.RED, Lamp.OFF, Lamp.OFF), result.fiveHoursRow)
        // 12 hours % 5 = 2 lamps on the 1-hour row
        assertEquals(listOf(Lamp.RED, Lamp.RED, Lamp.OFF, Lamp.OFF), result.oneHourRow)
        // 34 minutes / 5 = 6 lamps on. 3rd and 6th must indicate quarters (RED)
        assertEquals(
            listOf(
                Lamp.YELLOW, Lamp.YELLOW, Lamp.RED,
                Lamp.YELLOW, Lamp.YELLOW, Lamp.RED,
                Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF
            ),
            result.fiveMinutesRow
        )
        // 34 minutes % 5 = 4 lamps on the 1-minute row
        assertEquals(
            listOf(Lamp.YELLOW, Lamp.YELLOW, Lamp.YELLOW, Lamp.YELLOW),
            result.oneMinuteRow
        )
    }

    @Test
    fun `end of day 23-59-59 should turn all structural lamps on`() {
        val time = Time(hour = 23, minute = 59, second = 59)
        val result = useCase(time)

        assertEquals(listOf(Lamp.RED, Lamp.RED, Lamp.RED, Lamp.RED), result.fiveHoursRow)
        assertEquals(listOf(Lamp.RED, Lamp.RED, Lamp.RED, Lamp.OFF), result.oneHourRow)
        assertEquals(
            listOf(
                Lamp.YELLOW, Lamp.YELLOW, Lamp.RED,
                Lamp.YELLOW, Lamp.YELLOW, Lamp.RED,
                Lamp.YELLOW, Lamp.YELLOW, Lamp.RED,
                Lamp.YELLOW, Lamp.YELLOW
            ),
            result.fiveMinutesRow
        )
        assertEquals(
            listOf(Lamp.YELLOW, Lamp.YELLOW, Lamp.YELLOW, Lamp.YELLOW),
            result.oneMinuteRow
        )
    }
}