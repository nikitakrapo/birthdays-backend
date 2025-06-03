package com.nikitakrapo.features.users

import kotlinx.datetime.LocalDate

data class UserInfo(
    val uid: String,
    val displayName: String,
    val birthdayDate: LocalDate,
)
