package com.nikitakrapo.birthdays

import com.nikitakrapo.user.UserPrincipal
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.post

fun Routing.configureBirthdaysRouting() {
    post("/birthdays") {
        val user = call.principal<UserPrincipal>() ?: run{
            call.respond(HttpStatusCode.Unauthorized)
            return@post
        }
        call.respondText { user.uid }
    }
}