package com.nikitakrapo.features.birthdays

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

data class LocalBirthday(
    val id: String,
    val ownerUid: String,
    val displayName: String,
    val birthdayDate: LocalDate,
    val createdAt: Instant,
)