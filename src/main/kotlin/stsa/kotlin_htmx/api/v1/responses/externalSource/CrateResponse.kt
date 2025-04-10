package stsa.kotlin_htmx.api.v1.responses.externalSource

import kotlinx.serialization.Serializable

@Serializable
data class CrateResponse(
    val id: String,
    val name: String,
    val description: String? = null,
    val image: String
)
