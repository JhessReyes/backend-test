package stsa.kotlin_htmx.api.v1.services

import stsa.kotlin_htmx.api.v1.responses.externalSource.AgentResponse
import stsa.kotlin_htmx.api.v1.responses.externalSource.TeamResponse
import stsa.kotlin_htmx.database.repositories.AgentRepository
import stsa.kotlin_htmx.database.repositories.WhereAgent

class AgentService(private val repository: AgentRepository) {
    suspend fun getAgentsOnWhere(whereAgent: WhereAgent): List<AgentResponse> {
        val agents = repository.getAgents(whereAgent)
        return agents.map { i ->
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