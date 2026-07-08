package com.mohamed.kataberlinclock.domain.model

data class BerlinClock(
    val secondsLamp: Lamp,
    val fiveHoursRow: List<Lamp>,
    val oneHourRow: List<Lamp>,
    val fiveMinutesRow: List<Lamp>,
    val oneMinuteRow: List<Lamp>
) {
    init {
        require(fiveHoursRow.size == 4) { "Five hours row must contain exactly 4 lamps" }
        require(oneHourRow.size == 4) { "One hour row must contain exactly 4 lamps" }
        require(fiveMinutesRow.size == 11) { "Five minutes row must contain exactly 11 lamps" }
        require(oneMinuteRow.size == 4) { "One minute row must contain exactly 4 lamps" }
    }
}
