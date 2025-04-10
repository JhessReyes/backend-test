package stsa.kotlin_htmx.api.v1.responses.externalSource

import kotlinx.serialization.Serializable

@Serializable
data class AgentResponse(
    val id: String,
    val name: String,
    val description: String,
    val image: String,
    val team: TeamResponse
) {
    val teamId: String
        get() = team.id
}
