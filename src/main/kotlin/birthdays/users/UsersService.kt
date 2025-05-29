package com.nikitakrapo.birthdays.users

import com.nikitakrapo.db.tables.Users
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.jetbrains.exposed.v1.jdbc.upsertReturning

object UsersService {

    fun getUser(uid: String): UserInfo? = transaction {
        Users
            .selectAll()
            .where { Users.uid eq uid }
            .firstOrNull()
            ?.toUserInfo()
    }

    fun createUser(userInfo: UserInfo) = transaction {
        Users
            .upsertReturning {
                it[uid] = userInfo.uid
                it[diplayName] = userInfo.displayName
                it[birthdayDate] = userInfo.birthdayDate
            }
            .singleOrNull()
            ?.toUserInfo()
    }

    private fun ResultRow.toUserInfo(): UserInfo {
        return UserInfo(
            uid = get(Users.uid),
            displayName = get(Users.diplayName),
            birthdayDate = get(Users.birthdayDate),
        )
    }
}