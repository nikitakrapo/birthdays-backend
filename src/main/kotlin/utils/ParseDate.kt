package com.nikitakrapo.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents

fun String.parseLocalDateOrNull(): LocalDate? {
    return try {
        LocalDate.parse(this)
    } catch (e: IllegalArgumentException) {
        null
    }
}

fun LocalDate.toIsoString(): String {
    return format(LocalDate.Formats.ISO)
}

fun Instant.toIsoString(): String {
    return format(DateTimeComponents.Formats.ISO_DATE_TIME_OFFSET)
}