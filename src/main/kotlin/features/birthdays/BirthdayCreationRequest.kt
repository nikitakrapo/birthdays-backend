package com.nikitakrapo.features.birthdays

import kotlinx.datetime.LocalDate

data class BirthdayCreationRequest(
    val uid: String,
    val displayName: String,
    val birthdayDate: LocalDate,
)