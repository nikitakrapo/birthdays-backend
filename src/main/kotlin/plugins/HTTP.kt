package com.nikitakrapo.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.defaultheaders.DefaultHeaders
import io.ktor.server.plugins.openapi.openAPI
import io.ktor.server.routing.routing

fun Application.configureHTTP() {
    routing {
        openAPI(path = "openapi")
    }
    install(DefaultHeaders) {
        header("X-Engine", "Ktor") // will send this header with each response
    }
}
