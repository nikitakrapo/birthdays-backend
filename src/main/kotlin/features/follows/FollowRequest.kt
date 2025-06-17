package com.nikitakrapo.features.follows

data class FollowRequest(
    val followerUid: String,
    val followeeUid: String,
)
