package com.mohamed.kataberlinclock.presentation
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.mohamed.kataberlinclock.domain.model.BerlinClock
import com.mohamed.kataberlinclock.domain.model.Lamp
import com.mohamed.kataberlinclock.domain.model.Time
import com.mohamed.kataberlinclock.ui.BerlinClockScreenContent
import org.junit.Rule
import org.junit.Test

class BerlinClockScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun screen_displays_correct_digital_time_and_lamps() {

        val time = Time(12, 34, 5)
        val clock = BerlinClock(
            secondsLamp = Lamp.OFF,
            fiveHoursRow = listOf(Lamp.RED, Lamp.RED, Lamp.OFF, Lamp.OFF),
            oneHourRow = listOf(Lamp.RED, Lamp.RED, Lamp.OFF, Lamp.OFF),
            fiveMinutesRow = listOf(
                Lamp.YELLOW, Lamp.YELLOW, Lamp.RED,
                Lamp.YELLOW, Lamp.YELLOW, Lamp.RED,
                Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF
            ),
            oneMinuteRow = listOf(Lamp.YELLOW, Lamp.YELLOW, Lamp.YELLOW, Lamp.YELLOW)
        )
        composeTestRule.setContent {
            BerlinClockScreenContent(time = time, clock = clock)
        }
        composeTestRule.onNodeWithText("12:34:05").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Seconds lamp, Off lamp").assertIsDisplayed()
        composeTestRule.onAllNodesWithContentDescription("Red lamp").assertCountEquals(6)
        composeTestRule.onAllNodesWithContentDescription("Yellow lamp").assertCountEquals(8)
        composeTestRule.onAllNodesWithContentDescription("Off lamp").assertCountEquals(9)
    }
}