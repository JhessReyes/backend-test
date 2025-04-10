package stsa.kotlin_htmx.database.seeders

import stsa.kotlin_htmx.api.v1.responses.externalSource.*
import stsa.kotlin_htmx.api.v1.services.ExternalApiService

class DataSeeder(private val apiService: ExternalApiService) {
    suspend fun seed() {
        val agentsList: List<AgentResponse> = apiService.getAgentsData()
        val skinsList: List<SkinResponse> = apiService.getSkinsData()
        val keysList: List<KeyResponse> = apiService.getKeysData()
        val cratesList: List<CrateResponse> = apiService.getCratesData()

        val agentsTeamList = agentsList.map { it.team }.toSet()
        val skinsTeamList = skinsList.map { it.team }.toSet()
        val teamList = HashSet<TeamResponse>()
        teamList.addAll(agentsTeamList)
        teamList.addAll(skinsTeamList)
    }
}