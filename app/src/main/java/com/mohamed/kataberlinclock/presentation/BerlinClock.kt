package com.mohamed.kataberlinclock.presentation

import com.mohamed.kataberlinclock.domain.model.BerlinClock
import com.mohamed.kataberlinclock.domain.model.Time

internal sealed interface BerlinClockUiState {
    object Loading : BerlinClockUiState
    data class Success(val time: Time, val clock: BerlinClock) : BerlinClockUiState
}