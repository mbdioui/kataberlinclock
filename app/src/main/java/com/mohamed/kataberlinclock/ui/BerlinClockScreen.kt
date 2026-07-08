package com.mohamed.kataberlinclock.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohamed.kataberlinclock.data.util.format
import com.mohamed.kataberlinclock.domain.model.BerlinClock
import com.mohamed.kataberlinclock.domain.model.Lamp
import com.mohamed.kataberlinclock.domain.model.Time
import com.mohamed.kataberlinclock.presentation.BerlinClockUiState
import com.mohamed.kataberlinclock.presentation.BerlinClockViewModel

import com.mohamed.kataberlinclock.ui.component.LampCircle
import com.mohamed.kataberlinclock.ui.component.LampRow
import org.koin.androidx.compose.koinViewModel

@Composable
private fun BerlinClockScreen(
    modifier: Modifier = Modifier,
    viewModel: BerlinClockViewModel = koinViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    when (val currentState = state) {
        is BerlinClockUiState.Loading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Chargement...", color = Color.Black)
            }
        }
        is BerlinClockUiState.Success -> {
            BerlinClockScreenContent(
                time = currentState.time,
                clock = currentState.clock,
                modifier = modifier,
            )
        }
    }
}

@Composable
internal fun BerlinClockScreenContent(
    time: Time,
    clock: BerlinClock,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = time.format(),
            color = Color.Black,
            style = MaterialTheme.typography.displayLarge,
        )

        LampCircle(lamp = clock.secondsLamp)
        LampRow(lamps = clock.fiveHoursRow)
        LampRow(lamps = clock.oneHourRow)
        LampRow(lamps = clock.fiveMinutesRow)
        LampRow(lamps = clock.oneMinuteRow)
    }
}

@Preview(showBackground = true)
@Composable
private fun BerlinClockScreenPreview() {
    MaterialTheme {
        BerlinClockScreenContent(
            time = Time(19, 1, 12),
            clock = BerlinClock(
                secondsLamp = Lamp.YELLOW,
                fiveHoursRow = listOf(Lamp.RED, Lamp.RED, Lamp.YELLOW, Lamp.OFF),
                oneHourRow = listOf(Lamp.RED, Lamp.RED, Lamp.OFF, Lamp.OFF),
                fiveMinutesRow = List(11) { Lamp.OFF },
                oneMinuteRow = List(4) { Lamp.OFF },
            ),
        )
    }
}