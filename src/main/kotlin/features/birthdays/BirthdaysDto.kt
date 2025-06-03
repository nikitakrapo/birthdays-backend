package com.nikitakrapo.features.birthdays

import com.nikitakrapo.utils.exceptions.DtoParsingException
import com.nikitakrapo.utils.parseLocalDateOrNull
import com.nikitakrapo.utils.toIsoString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BirthdaysResponseDto(
    @SerialName("birthdays") val birthdays: List<LocalBirthdayDto>,
)

@Serializable
data class BirthdayCreationRequestDto(
    @SerialName("displayName") val displayName: String,
    @SerialName("birthdayDate") val birthdayDate: String,
)

@Serializable
data class BirthdayCreationResponseDto(
    @SerialName("birthday") val birthday: LocalBirthdayDto,
)

@Serializable
data class LocalBirthdayDto(
    @SerialName("id") val id: String,
    @SerialName("displayName") val displayName: String,
    @SerialName("birthdayDate") val birthdayDate: String,
    @SerialName("createdAt") val createdAt: String,
)

fun LocalBirthday.toDto(): LocalBirthdayDto {
    return LocalBirthdayDto(
        id = id,
        displayName = displayName,
        birthdayDate = birthdayDate.toIsoString(),
        createdAt = createdAt.toIsoString(),
    )
}

fun BirthdayCreationRequestDto.toDomain(uid: String): BirthdayCreationRequest {
    return BirthdayCreationRequest(
        uid = uid,
        displayName = displayName,
        birthdayDate = birthdayDate.parseLocalDateOrNull()
            ?: throw DtoParsingException("Error parsing BirthdayCreationRequestDto: wrong birthday date"),
    )
}
