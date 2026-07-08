package com.mohamed.kataberlinclock.domain.model


import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class TimeTest {

    @Test
    fun `valid time coordinates should not throw exception`() {
        assertDoesNotThrow {
            Time(hour = 0, minute = 0, second = 0)
            Time(hour = 23, minute = 59, second = 59)
            Time(hour = 24, minute = 0, second = 0)
        }
    }

    @Test
    fun `edge limits illegal argument exception`() {
        assertThrows(IllegalArgumentException::class.java) {
            Time(hour = -1, minute = 0, second = 0)
        }
        assertThrows(IllegalArgumentException::class.java) {
            Time(hour = 25, minute = 0, second = 0)
        }
    }

    @Test
    fun `edge limits minute values illegal argument exception`() {
        assertThrows(IllegalArgumentException::class.java) {
            Time(hour = 12, minute = -1, second = 0)
        }
        assertThrows(IllegalArgumentException::class.java) {
            Time(hour = 12, minute = 60, second = 0)
        }
    }

    @Test
    fun `edge limits second values illegal argument exception`() {
        assertThrows(IllegalArgumentException::class.java) {
            Time(hour = 12, minute = 30, second = -1)
        }
        assertThrows(IllegalArgumentException::class.java) {
            Time(hour = 12, minute = 30, second = 60)
        }
    }
}