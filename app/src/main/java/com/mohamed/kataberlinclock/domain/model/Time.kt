package com.mohamed.kataberlinclock.domain.model

data class Time(
    val hour: Int,
    val minute: Int,
    val second: Int
) {
    init {
        require(hour in 0..24)
        require(minute in 0..59)
        require(second in 0..59)
        if (hour == 24) {
            require(minute == 0 && second == 0)
        }
    }
}
