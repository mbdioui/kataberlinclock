package com.mohamed.kataberlinclock.ui.component
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohamed.kataberlinclock.domain.model.Lamp

@Composable
internal fun LampCircle(
    lamp: Lamp,
    modifier: Modifier = Modifier,
) {
    LampShape(
        lamp = lamp,
        description = "Seconds lamp, ${lamp.label()}",
        modifier = modifier.size(SECONDS_CIRCLE_SIZE),
        shape = CircleShape,
    )
}

@Composable
internal fun LampCapsule(
    lamp: Lamp,
    modifier: Modifier = Modifier,
    roundedStart: Boolean = false,
    roundedEnd: Boolean = false,
) {
    LampShape(
        lamp = lamp,
        description = lamp.label(),
        modifier = modifier.fillMaxHeight(),
        shape = RoundedCornerShape(
            topStartPercent = if (roundedStart) CAPSULE_ROUNDED_PERCENT else CAPSULE_ROUNDNESS_PERCENT,
            topEndPercent = if (roundedEnd) CAPSULE_ROUNDED_PERCENT else CAPSULE_ROUNDNESS_PERCENT,
            bottomEndPercent = if (roundedEnd) CAPSULE_ROUNDED_PERCENT else CAPSULE_ROUNDNESS_PERCENT,
            bottomStartPercent = if (roundedStart) CAPSULE_ROUNDED_PERCENT else CAPSULE_ROUNDNESS_PERCENT,
        ),
    )
}

@Composable
private fun LampShape(
    lamp: Lamp,
    description: String,
    modifier: Modifier,
    shape: Shape,
) {
    Box(
        modifier = modifier
            .background(color = lamp.toColor(), shape = shape)
            .border(width = BORDER_WIDTH, color = BORDER_COLOR, shape = shape)
            .semantics { contentDescription = description },
    )
}

private fun Lamp.toColor(): Color =
    when (this) {
        Lamp.OFF -> Color.White
        Lamp.YELLOW -> Color.Yellow
        Lamp.RED -> Color.Red
    }

private fun Lamp.label(): String =
    when (this) {
        Lamp.OFF -> "Off lamp"
        Lamp.YELLOW -> "Yellow lamp"
        Lamp.RED -> "Red lamp"
    }

@Preview(showBackground = true)
@Composable
private fun LampCirclePreview() {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LampCircle(lamp = Lamp.OFF)
        LampCircle(lamp = Lamp.YELLOW)
        LampCircle(lamp = Lamp.RED)
    }
}

@Preview(showBackground = true)
@Composable
private fun LampCapsulePreview() {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .height(50.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LampCapsule(lamp = Lamp.RED, modifier = Modifier.width(60.dp), roundedStart = true)
        LampCapsule(lamp = Lamp.YELLOW, modifier = Modifier.width(60.dp))
        LampCapsule(lamp = Lamp.OFF, modifier = Modifier.width(60.dp), roundedEnd = true)
    }
}

private val SECONDS_CIRCLE_SIZE = 64.dp
private val BORDER_WIDTH = 4.dp
private val BORDER_COLOR = Color.DarkGray
private const val CAPSULE_ROUNDNESS_PERCENT = 10
private const val CAPSULE_ROUNDED_PERCENT = 35