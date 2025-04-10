package stsa.kotlin_htmx.api.v1.services

import stsa.kotlin_htmx.api.v1.responses.externalSource.AgentResponse
import stsa.kotlin_htmx.api.v1.responses.externalSource.TeamResponse
import stsa.kotlin_htmx.api.v1.utils.CacheManager
import stsa.kotlin_htmx.database.models.AgentModel
import stsa.kotlin_htmx.database.repositories.AgentRepository
import stsa.kotlin_htmx.database.repositories.WhereAgent
import java.util.concurrent.TimeUnit

class AgentService(private val repository: AgentRepository) {
    private val agentCache = CacheManager<String, List<AgentModel>>(
        ttlMillis = TimeUnit.MINUTES.toMillis(10)
    )

    suspend fun getAgentsOnWhere(whereAgent: WhereAgent): List<AgentResponse> {
        val filters = mapOf(
            "id" to whereAgent.id,
            "name" to whereAgent.name,
            "description" to whereAgent.description,
            "image" to whereAgent.image,
            "teamName" to whereAgent.teamName
        )
        val key = agentCache.generateKey("agents", filters = filters)

        val cachedAgents = agentCache.get(key) ?: run {
            val crates = repository.getAgents(whereAgent)
            agentCache.put(key, crates)
            crates
        }

        return cachedAgents.map { i ->
            AgentResponse(
                id = i.id,
                name = i.name,
                description = i.description,
                image = i.image,
                team = TeamResponse(
                    id = i.team.id,
                    name = i.team.name
                )
            )
        }
    }
}