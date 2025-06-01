package com.nikitakrapo.birthdays.users

import com.nikitakrapo.plugins.security.UserPrincipal
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.principal
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.post

fun Routing.configureUsersRouting() {

    authenticate {
        get("/user") {
            val principal = call.principal<UserPrincipal>() ?: run {
                call.respond(HttpStatusCode.Unauthorized)
                return@get
            }

            val fullUser = UsersService.getUser(principal.uid)
                ?.toFullDto()
                ?: run {
                    call.respond(HttpStatusCode.InternalServerError)
                    return@get
                }

            call.respond(fullUser)
        }

        post("/user") {
            val principal = call.principal<UserPrincipal>() ?: run {
                call.respond(HttpStatusCode.Unauthorized)
                return@post
            }

            val userInfo = call.receive<UserInfoCreationRequestDto>()
                .toDomain(uid = principal.uid)
                ?: run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }

            val createdFullUser = UsersService.createUser(userInfo)
                ?.toFullDto()
                ?: run {
                    call.respond(HttpStatusCode.InternalServerError)
                    return@post
                }

            call.respond(createdFullUser)
        }
    }
}