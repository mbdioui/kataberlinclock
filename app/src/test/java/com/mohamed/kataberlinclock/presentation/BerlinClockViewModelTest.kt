package com.mohamed.kataberlinclock.presentation
import app.cash.turbine.test
import com.mohamed.kataberlinclock.domain.model.BerlinClock
import com.mohamed.kataberlinclock.domain.model.Lamp
import com.mohamed.kataberlinclock.domain.model.Time
import com.mohamed.kataberlinclock.domain.repository.TimeRepository
import com.mohamed.kataberlinclock.domain.usecase.ConvertToBerlinClockUseCase
import com.mohamed.kataberlinclock.util.MainDispatcherExtension
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class BerlinClockViewModelTest {

    @RegisterExtension
    @JvmField
    val mainDispatcherExtension = MainDispatcherExtension()

    private val timeRepository: TimeRepository = mockk()
    private val convertToBerlinClockUseCase = ConvertToBerlinClockUseCase()

    @Test
    fun `viewModel_initialization_success_ui_state`() = runTest {
        val timeFlow = MutableSharedFlow<Time>()
        every { timeRepository.getLiveTime() } returns timeFlow

        val viewModel = BerlinClockViewModel(timeRepository, convertToBerlinClockUseCase)

        viewModel.uiState.test {
            assertEquals(BerlinClockUiState.Loading, awaitItem())

            val sampleTime = Time(12, 0, 0)
            timeFlow.emit(sampleTime)

            val successState = awaitItem() as BerlinClockUiState.Success
            assertEquals(Lamp.YELLOW, successState.clock.secondsLamp)
            ensureAllEventsConsumed()
        }
    }
}
