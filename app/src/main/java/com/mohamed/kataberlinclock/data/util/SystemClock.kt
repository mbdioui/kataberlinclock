package com.mohamed.kataberlinclock.data.util

import com.mohamed.kataberlinclock.domain.model.Clock
import com.mohamed.kataberlinclock.domain.model.Time

import java.util.Calendar

internal class SystemClock : Clock {
    override fun getCurrentTime(): Time {
        val calendar = Calendar.getInstance()
        return Time(
            hour = calendar.get(Calendar.HOUR_OF_DAY),
            minute = calendar.get(Calendar.MINUTE),
            second = calendar.get(Calendar.SECOND)
        )
    }
}