package com.nikitakrapo.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.format

fun String.parseLocalDateOrNull(): LocalDate? {
    return try {
        LocalDate.parse(this)
    } catch (e: IllegalArgumentException) {
        null
    }
}

fun LocalDate.encodeAsISO(): String? {
    return try {
        format(LocalDate.Formats.ISO)
    } catch (e: IllegalArgumentException) {
        null
    }
}