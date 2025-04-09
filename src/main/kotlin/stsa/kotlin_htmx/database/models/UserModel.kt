package stsa.kotlin_htmx.database.models


data class UserModel(
    val id: String,
    val name: String,
    val email: String,
    val password: String,
    val salt: String
)
