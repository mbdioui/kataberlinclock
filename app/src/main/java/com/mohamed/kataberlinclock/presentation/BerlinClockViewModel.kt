package com.mohamed.kataberlinclock.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamed.kataberlinclock.domain.repository.TimeRepository
import com.mohamed.kataberlinclock.domain.usecase.ConvertToBerlinClockUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

internal class BerlinClockViewModel(
    timeRepository: TimeRepository,
    private val convertToBerlinClockUseCase: ConvertToBerlinClockUseCase
) : ViewModel() {

    val uiState: StateFlow<BerlinClockUiState> = timeRepository.getLiveTime()
        .map { time -> BerlinClockUiState.Success(time,convertToBerlinClockUseCase(time)) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = BerlinClockUiState.Loading
        )
}

