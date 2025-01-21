package com.example.data.models

import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.toList
import org.litote.kmongo.eq

class MongoDataSource(
    val db: CoroutineDatabase
) : UserDataSource {

    private val users = db.getCollection<User>(collectionName = "user")

    override suspend fun getUserByUsername(username: String): User? {
        return users.findOne(User::username eq username)
    }

    override suspend fun insertUser(user: User): Boolean {
        return users.insertOne(user).wasAcknowledged()
    }

    override suspend fun getAllUser(): List<User> {
        return users.collection.find().toList()
    }

}