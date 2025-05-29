package com.nikitakrapo

import com.nikitakrapo.db.connectDb
import com.nikitakrapo.plugins.configureFrameworks
import com.nikitakrapo.plugins.configureHTTP
import com.nikitakrapo.plugins.configureSerialization
import com.nikitakrapo.plugins.security.configureSecurity
import io.ktor.server.application.Application

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    connectDb()
    configureFrameworks()
    configureSerialization()
    configureSecurity()
    configureHTTP()
    configureRouting()
}
