package stsa.kotlin_htmx.api.v1.requests

import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    val name: String,
    val password: String
)
