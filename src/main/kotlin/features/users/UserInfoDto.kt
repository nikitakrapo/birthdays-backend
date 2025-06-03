package com.nikitakrapo.features.users

import com.nikitakrapo.utils.exceptions.DtoParsingException
import com.nikitakrapo.utils.parseLocalDateOrNull
import com.nikitakrapo.utils.toIsoString
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

fun UserInfoCreationRequestDto.toDomain(uid: String): UserInfo {
    return UserInfo(
        uid = uid,
        displayName = displayName,
        birthdayDate = birthdayDate.parseLocalDateOrNull()
            ?: throw DtoParsingException("Error parsing UserInfoCreationRequestDto: wrong birthdayDate"),
    )
}

fun UserInfo.toFullDto(): UserInfoDto {
    return UserInfoDto(
        uid = uid,
        displayName = displayName,
        birthdayDate = birthdayDate.toIsoString(),
    )
}