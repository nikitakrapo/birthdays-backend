package com.nikitakrapo.features.follows

import com.nikitakrapo.db.tables.UserFollows
import com.nikitakrapo.db.tables.UserFollows.FriendStatus
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.SqlExpressionBuilder.eq
import org.jetbrains.exposed.v1.core.and
import org.jetbrains.exposed.v1.jdbc.deleteWhere
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.jetbrains.exposed.v1.jdbc.update
import org.jetbrains.exposed.v1.jdbc.upsert

object FollowsService {

    /**
     * Returns all outgoing follow requests which are not yet accepted
     *
     * That means it also returns rejected ones
     *
     * @return list of follow requests
     */
    fun getOutgoingFollowRequests(userUid: String): List<FollowRequest> = transaction {
        UserFollows
            .selectAll()
            .where { UserFollows.followerUid eq userUid }
            .toList()
            .map { it.toFollowRequest() }
    }

    /**
     * Creates follow request from [userUid] to [followeeUid]
     *
     * If request already exists, nothing happens
     */
    fun createFollowRequest(userUid: String, followeeUid: String): Unit = transaction {
        // Insert with default status (pending) if none exists
        UserFollows
            .upsert {
                it[this.followerUid] = userUid
                it[this.followeeUid] = followeeUid
            }
    }

    /**
     * Cancels follow request
     */
    fun cancelFollowRequest(userUid: String, followeeUid: String): Unit = transaction {
        UserFollows
            .deleteWhere {
                (UserFollows.followerUid eq userUid) and (UserFollows.followeeUid eq followeeUid)
            }
    }

    /**
     * Returns all incoming follow requests which are not rejected nor accepted
     *
     * @return list of follow requests
     */
    fun getIncomingFollowRequests(userUid: String): List<FollowRequest> = transaction {
        UserFollows
            .selectAll()
            .where { UserFollows.followeeUid eq userUid }
            .toList()
            .map { it.toFollowRequest() }
    }

    /**
     * Accepts [requestingUserUid]'s request to follow [acceptingUserUid]
     */
    fun acceptFollowRequest(acceptingUserUid: String, requestingUserUid: String): Unit = transaction {
        UserFollows
            .update({ (UserFollows.followerUid eq requestingUserUid) and (UserFollows.followeeUid eq acceptingUserUid) }) {
                it[this.status] = FriendStatus.ACCEPTED.value
            }
    }

    /**
     * Rejects [requestingUserUid]'s request to follow [rejectingUserUid]
     */
    fun rejectFollowRequest(rejectingUserUid: String, requestingUserUid: String): Unit = transaction {
        UserFollows
            .update({ (UserFollows.followerUid eq requestingUserUid) and (UserFollows.followeeUid eq rejectingUserUid) }) {
                it[this.status] = FriendStatus.REJECTED.value
            }
    }

    private fun ResultRow.toFollowRequest(): FollowRequest {
        return FollowRequest(
            followerUid = get(UserFollows.followerUid),
            followeeUid = get(UserFollows.followerUid),
        )
    }
}