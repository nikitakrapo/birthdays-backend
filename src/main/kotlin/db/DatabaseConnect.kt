package com.nikitakrapo.db

import com.nikitakrapo.environment.BirthdaysEnv
import io.ktor.server.application.Application
import org.jetbrains.exposed.v1.jdbc.Database

fun Application.connectDb() {
    Database.connect(
        url = "jdbc:postgresql://${BirthdaysEnv.dbHost}:5432/${BirthdaysEnv.dbName}",
        driver = "org.postgresql.Driver",
        user = BirthdaysEnv.dbUsername,
        password = BirthdaysEnv.dbPassword,
    )
}