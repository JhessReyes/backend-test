package stsa.kotlin_htmx.database.repositories

import stsa.kotlin_htmx.database.models.AgentModel

data class WhereAgent(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val image: String? = null,
    val teamName: String? = null
)

interface AgentRepository {
    suspend fun getAgents(where: WhereAgent): List<AgentModel>
}
