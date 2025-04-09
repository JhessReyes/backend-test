package stsa.kotlin_htmx.api.v1.responses

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: String,
    val name: String,
    val email: String,
    val password: String,
    val salt: String
)
