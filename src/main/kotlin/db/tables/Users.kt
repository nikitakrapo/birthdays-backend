package com.nikitakrapo.db.tables

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.datetime.CurrentDateTime
import org.jetbrains.exposed.v1.datetime.date
import org.jetbrains.exposed.v1.datetime.datetime

object Users : Table(name = "users") {
    val uid = text("uid").autoIncrement()
    val diplayName = varchar("display_name", 50)
    val birthdayDate = date("birthday_date")
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    override val primaryKey = PrimaryKey(uid)
}