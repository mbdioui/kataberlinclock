package com.mohamed.kataberlinclock.presentation

import com.mohamed.kataberlinclock.domain.model.BerlinClock

internal sealed interface BerlinClockUiState {
    object Loading : BerlinClockUiState
    data class Success(val clock: BerlinClock) : BerlinClockUiState
}