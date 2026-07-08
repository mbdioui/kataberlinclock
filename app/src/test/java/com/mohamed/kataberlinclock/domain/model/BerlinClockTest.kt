package com.mohamed.kataberlinclock.domain.model

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class BerlinClockTest {

    @Test
    fun `correctly ccontracted BerlinClock should not throw exception`() {
        assertDoesNotThrow {
            BerlinClock(
                secondsLamp = Lamp.OFF,
                fiveHoursRow = List(4) { Lamp.OFF },
                oneHourRow = List(4) { Lamp.OFF },
                fiveMinutesRow = List(11) { Lamp.OFF },
                oneMinuteRow = List(4) { Lamp.OFF }
            )
        }
    }

    @Test
    fun `BerlinClock check integrity exceptions`() {
        assertThrows(IllegalArgumentException::class.java) {
            BerlinClock(Lamp.OFF, List(5) { Lamp.OFF }, List(4) { Lamp.OFF }, List(11) { Lamp.OFF }, List(4) { Lamp.OFF })
        }
        assertThrows(IllegalArgumentException::class.java) {
            BerlinClock(Lamp.OFF, List(4) { Lamp.OFF }, List(3) { Lamp.OFF }, List(11) { Lamp.OFF }, List(4) { Lamp.OFF })
        }
        assertThrows(IllegalArgumentException::class.java) {
            BerlinClock(Lamp.OFF, List(4) { Lamp.OFF }, List(4) { Lamp.OFF }, List(10) { Lamp.OFF }, List(4) { Lamp.OFF })
        }
    }
}