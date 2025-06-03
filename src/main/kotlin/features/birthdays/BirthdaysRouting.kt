package com.nikitakrapo.features.birthdays

import com.nikitakrapo.plugins.security.UserPrincipal
import com.nikitakrapo.plugins.security.requirePrincipal
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.post

fun Routing.configureBirthdaysRouting() {
    authenticate {
        get("/birthdays") {
            val user = requirePrincipal<UserPrincipal>()

            val offset = call.queryParameters["offset"]?.toLongOrNull()
            val count = call.queryParameters["count"]?.toIntOrNull()

            if (offset == null || count == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }

            val birthdays = BirthdaysService
                .getBirthdays(uid = user.uid, offset = offset, size = count)
                .map { it.toDto() }

            val response = BirthdaysResponseDto(
                birthdays = birthdays,
            )

            call.respond(response)
        }

        post("/birthdays") {
            val user = requirePrincipal<UserPrincipal>()

            val request = call.receive<BirthdayCreationRequestDto>()
                .toDomain(uid = user.uid)

            val createdBirthday = BirthdaysService
                .createBirthday(request = request)

            val response = BirthdayCreationResponseDto(
                birthday = createdBirthday.toDto(),
            )

            call.respond(response)
        }
    }
}