package com.nikitakrapo

import com.nikitakrapo.birthdays.configureBirthdaysRouting
import com.nikitakrapo.birthdays.users.configureUsersRouting
import io.ktor.server.application.Application
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World! It's an api")
        }

        configureUsersRouting()
        configureBirthdaysRouting()
    }
}
