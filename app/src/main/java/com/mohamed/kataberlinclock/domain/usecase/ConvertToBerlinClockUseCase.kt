package com.mohamed.kataberlinclock.domain.usecase

import com.mohamed.kataberlinclock.domain.model.BerlinClock
import com.mohamed.kataberlinclock.domain.model.Lamp
import com.mohamed.kataberlinclock.domain.model.Time

internal class ConvertToBerlinClockUseCase {

    operator fun invoke(time: Time): BerlinClock {
        return BerlinClock(
            secondsLamp = if (time.second % 2 == 0) Lamp.YELLOW else Lamp.OFF,
            fiveHoursRow = createStandardRow(time.hour / 5, 4, Lamp.RED),
            oneHourRow = createStandardRow(time.hour % 5, 4, Lamp.RED),
            fiveMinutesRow = createFiveMinutesRow(time.minute),
            oneMinuteRow = createStandardRow(time.minute % 5, 4, Lamp.YELLOW)
        )
    }

    private fun createStandardRow(onCount: Int, size: Int, color: Lamp): List<Lamp> {
        return List(size) { index ->
            if (index < onCount) color else Lamp.OFF
        }
    }

    private fun createFiveMinutesRow(minutes: Int): List<Lamp> {
        val onCount = minutes / 5
        return List(11) { index ->
            if (index < onCount) getFiveMinuteLampColor(index) else Lamp.OFF
        }
    }

    private fun getFiveMinuteLampColor(index: Int): Lamp {
        val isQuarterMark = (index + 1) % 3 == 0
        return if (isQuarterMark) Lamp.RED else Lamp.YELLOW
    }
}