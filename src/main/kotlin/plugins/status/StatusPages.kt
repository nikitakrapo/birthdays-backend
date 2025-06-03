package com.nikitakrapo.plugins.status

import com.nikitakrapo.utils.exceptions.UnauthorizedException
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.ContentTransformationException
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respondText

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<ContentTransformationException> { call, cause ->
            call.respondText("400: $cause", status = HttpStatusCode.BadRequest)
        }

        exception<UnauthorizedException> { call, cause ->
            call.respondText("401: $cause", status = HttpStatusCode.Unauthorized)
        }
    }
}