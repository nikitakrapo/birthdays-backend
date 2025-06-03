package com.nikitakrapo

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.nikitakrapo.db.connectDb
import com.nikitakrapo.plugins.configureFrameworks
import com.nikitakrapo.plugins.configureHTTP
import com.nikitakrapo.plugins.configureSerialization
import com.nikitakrapo.plugins.security.configureSecurity
import com.nikitakrapo.plugins.status.configureStatusPages
import io.ktor.server.application.Application

fun main(args: Array<String>) {
    FirebaseApp.initializeApp()
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    connectDb()
    configureFrameworks()
    configureSerialization()
    configureStatusPages()

    val firebaseAuth = FirebaseAuth.getInstance()
    configureSecurity(firebaseAuth)
    configureHTTP()
    configureRouting()
}
