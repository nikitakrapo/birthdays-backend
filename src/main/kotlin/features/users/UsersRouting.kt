package com.nikitakrapo.features.users

import com.nikitakrapo.plugins.security.UserPrincipal
import com.nikitakrapo.plugins.security.requirePrincipal
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.post

fun Routing.configureUsersRouting() {

    authenticate {
        get("/users") {
            val user = requirePrincipal<UserPrincipal>()

            val fullUser = UsersService.getUser(user.uid)
                .toFullDto()

            call.respond(fullUser)
        }

        post("/users") {
            val user = requirePrincipal<UserPrincipal>()

            val userInfo = call.receive<UserInfoCreationRequestDto>()
                .toDomain(uid = user.uid)

            val createdFullUser = UsersService.createUser(userInfo)
                .toFullDto()

            call.respond(createdFullUser)
        }
    }
}