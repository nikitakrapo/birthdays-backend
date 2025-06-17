package com.nikitakrapo.features.follows

import com.nikitakrapo.plugins.security.UserPrincipal
import com.nikitakrapo.plugins.security.requirePrincipal
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.plugins.MissingRequestParameterException
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post

private const val userUidPathParam = "userId"
private const val followerUidPathParam = "followerId"

fun Routing.configureFollowsRouting() {
    authenticate {
        // Get all outgoing follow requests
        get("/users/me/follow-requests/outgoing") {
            val user = requirePrincipal<UserPrincipal>()

            val requests = FollowsService
                .getOutgoingFollowRequests(userUid = user.uid)
                .map { it.toDto() }

            val response = FollowRequestsResponseDto(
                requests = requests,
            )

            call.respond(response)
        }

        // Create follow request
        post("/users/{$userUidPathParam}/follow") {
            val user = requirePrincipal<UserPrincipal>()

            val uidToFollow = call.pathParameters[userUidPathParam]
                ?: throw MissingRequestParameterException("No $userUidPathParam passed")

            FollowsService
                .createFollowRequest(
                    userUid = user.uid,
                    followeeUid = uidToFollow,
                )

            call.respond(HttpStatusCode.OK)
        }

        // Cancel follow request
        delete("users/{$userUidPathParam}/follow") {
            val user = requirePrincipal<UserPrincipal>()

            val uidToUnfollow = call.pathParameters[userUidPathParam]
                ?: throw MissingRequestParameterException("No $userUidPathParam passed")

            FollowsService
                .cancelFollowRequest(
                    userUid = user.uid,
                    followeeUid = uidToUnfollow,
                )

            call.respond(HttpStatusCode.OK)
        }

        // Get all incoming follow requests
        get("/users/me/follow-requests/incoming") {
            val user = requirePrincipal<UserPrincipal>()

            val requests = FollowsService
                .getIncomingFollowRequests(userUid = user.uid)
                .map { it.toDto() }

            val response = FollowRequestsResponseDto(
                requests = requests,
            )

            call.respond(response)
        }

        // Accept follow request
        post("/users/me/followers/{$followerUidPathParam}/accept") {
            val user = requirePrincipal<UserPrincipal>()

            val uidToAccept = call.pathParameters[followerUidPathParam]
                ?: throw MissingRequestParameterException("No $followerUidPathParam passed")

            FollowsService
                .acceptFollowRequest(
                    acceptingUserUid = user.uid,
                    requestingUserUid = uidToAccept,
                )

            call.respond(HttpStatusCode.OK)
        }

        // Reject follow request
        post("/users/me/followers/{$followerUidPathParam}/reject") {
            val user = requirePrincipal<UserPrincipal>()

            val uidToReject = call.pathParameters[followerUidPathParam]
                ?: throw MissingRequestParameterException("No $followerUidPathParam passed")

            FollowsService
                .rejectFollowRequest(
                    rejectingUserUid = user.uid,
                    requestingUserUid = uidToReject,
                )

            call.respond(HttpStatusCode.OK)
        }
    }
}