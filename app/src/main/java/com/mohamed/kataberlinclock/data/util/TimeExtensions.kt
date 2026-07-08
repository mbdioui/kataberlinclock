package com.mohamed.kataberlinclock.data.util

import com.mohamed.kataberlinclock.domain.model.Time
import java.util.Locale

internal fun Time.format(): String = String.format(Locale.getDefault(), "%02d:%02d:%02d", hour, minute, second)