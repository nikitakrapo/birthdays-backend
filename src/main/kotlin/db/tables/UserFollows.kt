package com.nikitakrapo.db.tables

import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.datetime.CurrentDateTime
import org.jetbrains.exposed.v1.datetime.datetime

/**
 * Depicts one-way following system with approval logic
 */
object UserFollows : Table("user_follows") {

    val followerUid = text("follower_uid")
        .references(Users.uid, onDelete = ReferenceOption.CASCADE)
    val followeeUid = text("followee_uid")
        .references(Users.uid, onDelete = ReferenceOption.CASCADE)

    val status = text("status")
        .check { it inList FriendStatus.entries.map { it.value } }
        .default(FriendStatus.PENDING.value)

    val requestedAt = datetime("requested_at")
        .defaultExpression(CurrentDateTime)
    val updatedAt = datetime("updated_at")
        .defaultExpression(CurrentDateTime)

    override val primaryKey: PrimaryKey = PrimaryKey(followerUid, followeeUid)

    enum class FriendStatus(val value: String) {
        PENDING("pending"),
        ACCEPTED("accepted"),
        REJECTED("rejected"),
        ;
    }
}