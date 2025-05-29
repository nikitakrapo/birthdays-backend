package com.nikitakrapo.birthdays.users

import com.nikitakrapo.utils.encodeAsISO
import com.nikitakrapo.utils.parseLocalDateOrNull
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoDto(
    @SerialName("uid") val uid: String,
    @SerialName("displayName") val displayName: String,
    @SerialName("birthdayDate") val birthdayDate: String,
)

@Serializable
data class UserInfoCreationRequestDto(
    @SerialName("displayName") val displayName: String,
    @SerialName("birthdayDate") val birthdayDate: String,
)

fun UserInfoCreationRequestDto.toDomain(uid: String): UserInfo? {
    return UserInfo(
        uid = uid,
        displayName = displayName,
        birthdayDate = birthdayDate.parseLocalDateOrNull() ?: return null,
    )
}

fun UserInfo.toFullDto(): UserInfoDto? {
    return UserInfoDto(
        uid = uid,
        displayName = displayName,
        birthdayDate = birthdayDate.encodeAsISO() ?: return null,
    )
}