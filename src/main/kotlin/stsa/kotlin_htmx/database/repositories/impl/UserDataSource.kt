package stsa.kotlin_htmx.database.repositories.impl

import kotlinx.coroutines.CoroutineDispatcher
import stsa.kotlin_htmx.database.models.UserModel
import stsa.kotlin_htmx.database.repositories.UserRepository

class UserDataSource(db: CoroutineDispatcher) : UserRepository {
    override suspend fun getUserByEmail(id: String): UserModel {
        TODO("Not yet implemented")
    }

    override suspend fun insertUser() {
        TODO("Not yet implemented")
    }
}