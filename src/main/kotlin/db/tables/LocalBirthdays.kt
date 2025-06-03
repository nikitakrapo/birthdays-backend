package com.nikitakrapo.db.tables

import java.util.*
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.datetime.CurrentTimestamp
import org.jetbrains.exposed.v1.datetime.date
import org.jetbrains.exposed.v1.datetime.timestamp

object LocalBirthdays : Table() {
    val id = uuid("id").default(UUID.randomUUID())
    val ownerUid = varchar("owner_uid", 50)
    val displayName = varchar("display_name", 50)
    val birthdayDate = date("birthday_date")
    val createdAt = timestamp("created_at").defaultExpression(CurrentTimestamp)

    override val primaryKey = PrimaryKey(id)

    init {
        foreignKey(ownerUid, target = Users.primaryKey)
    }
}