package com.nikitakrapo.plugins.security

import com.kborowy.authprovider.firebase.firebase
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import java.io.File

fun Application.configureSecurity() {
    install(Authentication) {
        firebase {
            adminFile = File("secrets/birthdays-firebase.json")
            validate { token ->
                UserPrincipal(uid = token.uid)
            }
        }
    }
}
