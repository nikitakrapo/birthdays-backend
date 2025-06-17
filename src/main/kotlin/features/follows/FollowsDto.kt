package com.nikitakrapo.features.follows

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FollowRequestDto(
    @SerialName("followerUid") val followerUid: String,
    @SerialName("followeeUid") val followeeUid: String,
)

@Serializable
data class FollowRequestsResponseDto(
    @SerialName("requests") val requests: List<FollowRequestDto>,
)

fun FollowRequest.toDto(): FollowRequestDto {
    return FollowRequestDto(
        followerUid = followerUid,
        followeeUid = followeeUid,
    )
}