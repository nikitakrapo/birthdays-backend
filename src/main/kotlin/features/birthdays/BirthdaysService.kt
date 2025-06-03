package com.nikitakrapo.features.birthdays

import com.nikitakrapo.db.tables.LocalBirthdays
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.jdbc.insertReturning
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

object BirthdaysService {

    fun getBirthdays(
        uid: String,
        offset: Long,
        size: Int
    ): List<LocalBirthday> = transaction {
        LocalBirthdays
            .selectAll()
            .orderBy(LocalBirthdays.birthdayDate)
            .where { LocalBirthdays.ownerUid eq uid }
            .offset(offset)
            .limit(size)
            .map { it.toLocalBirthday() }
    }

    fun createBirthday(
        request: BirthdayCreationRequest,
    ): LocalBirthday = transaction {
        LocalBirthdays
            .insertReturning {
                it[ownerUid] = request.uid
                it[displayName] = request.displayName
                it[birthdayDate] = request.birthdayDate
            }
            .single()
            .toLocalBirthday()
    }

    private fun ResultRow.toLocalBirthday(): LocalBirthday {
        return LocalBirthday(
            id = get(LocalBirthdays.id).toString(),
            ownerUid = get(LocalBirthdays.ownerUid),
            displayName = get(LocalBirthdays.displayName),
            birthdayDate = get(LocalBirthdays.birthdayDate),
            createdAt = get(LocalBirthdays.createdAt),
        )
    }
}