package com.mohamed.kataberlinclock.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohamed.kataberlinclock.domain.model.Lamp

@Composable
internal fun LampRow(
    lamps: List<Lamp>,
    modifier: Modifier = Modifier,
) {
    val isMinutesOfFive = lamps.size == MINUTES_OF_FIVE_LAMP_COUNT
    val height = if (isMinutesOfFive) TALL_ROW_HEIGHT else SHORT_ROW_HEIGHT
    val spacing = if (isMinutesOfFive) SMALL_SPACING else LARGE_SPACING

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        horizontalArrangement = Arrangement.spacedBy(spacing),
    ) {
        lamps.forEachIndexed { index, lamp ->
            LampCapsule(
                lamp = lamp,
                modifier = Modifier.weight(1f),
                roundedStart = index == 0,
                roundedEnd = index == lamps.size - 1,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LampRowPreview() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        //4 lamps
        LampRow(
            lamps = listOf(Lamp.RED, Lamp.RED, Lamp.OFF, Lamp.OFF)
        )
        // 11 LAMPS FOR 1/4 of hour
        LampRow(
            lamps = listOf(
                Lamp.YELLOW, Lamp.YELLOW, Lamp.RED,
                Lamp.YELLOW, Lamp.YELLOW, Lamp.RED,
                Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF
            )
        )
    }
}

private const val MINUTES_OF_FIVE_LAMP_COUNT = 11
private val SMALL_SPACING = 4.dp
private val LARGE_SPACING = 8.dp
private val TALL_ROW_HEIGHT = 56.dp
private val SHORT_ROW_HEIGHT = 36.dp