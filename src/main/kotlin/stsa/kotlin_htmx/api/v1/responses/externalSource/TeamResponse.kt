package stsa.kotlin_htmx.api.v1.responses.externalSource

import kotlinx.serialization.Serializable

@Serializable
data class TeamResponse(
    val id: String,
    val name: String
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as TeamResponse
        return id == other.id // Compare id.
    }

    override fun hashCode(): Int {
        return id.hashCode() // Use id to calculate hash.
    }
}