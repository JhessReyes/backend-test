package stsa.kotlin_htmx.database.repositories

import stsa.kotlin_htmx.database.models.UserModel

interface UserRepository {
    suspend fun getUserByEmail(id: String): UserModel
    suspend fun insertUser()
}