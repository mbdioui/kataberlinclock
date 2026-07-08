package com.mohamed.kataberlinclock.di

import com.mohamed.kataberlinclock.data.repository.SystemTimeRepository
import com.mohamed.kataberlinclock.data.util.SystemClock
import com.mohamed.kataberlinclock.domain.model.Clock
import com.mohamed.kataberlinclock.domain.repository.TimeRepository
import com.mohamed.kataberlinclock.domain.usecase.ConvertToBerlinClockUseCase
import com.mohamed.kataberlinclock.presentation.BerlinClockViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val appModule = module {
    singleOf(::SystemClock) { bind<Clock>() }

    singleOf(::SystemTimeRepository) { bind<TimeRepository>() }

    factory { ConvertToBerlinClockUseCase() }


    viewModelOf(::BerlinClockViewModel)
}