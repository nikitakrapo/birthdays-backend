package com.nikitakrapo.environment

object BirthdaysEnv {

    val dbHost get() = System.getenv("DB_HOST")

    val dbName get() = System.getenv("DB_NAME")
    val dbUsername get() = System.getenv("DB_USER")
    val dbPassword get() = System.getenv("DB_PASSWORD")
}