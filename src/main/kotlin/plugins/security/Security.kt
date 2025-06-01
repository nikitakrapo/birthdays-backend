package com.nikitakrapo.plugins.security

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseToken
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.bearer

fun Application.configureSecurity(firebaseAuth: FirebaseAuth) {
    install(Authentication) {
        bearer {
            authenticate { bearerTokenCredential ->
                val decodedToken = parseFirebaseToken(
                    firebaseAuth = firebaseAuth,
                    token = bearerTokenCredential.token
                )
                decodedToken?.let { firebaseToken ->
                    UserPrincipal(uid = firebaseToken.uid)
                }
            }
        }
    }
}

private fun parseFirebaseToken(firebaseAuth: FirebaseAuth, token: String): FirebaseToken? {
    return try {
        firebaseAuth.verifyIdToken(token)
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
        null
    }
}
