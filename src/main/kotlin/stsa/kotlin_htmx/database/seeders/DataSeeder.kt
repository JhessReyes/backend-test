package stsa.kotlin_htmx.database.seeders

import io.ktor.server.html.*
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.transactions.transaction
import stsa.kotlin_htmx.api.v1.responses.externalSource.*
import stsa.kotlin_htmx.api.v1.services.ExternalApiService
import stsa.kotlin_htmx.database.DatabaseFactory
import stsa.kotlin_htmx.database.models.*
import org.jetbrains.exposed.sql.insert

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

        println(teamList)
        transaction {
            // TODO: create transaction seeders here
            teamList.forEach { team ->
                Team.insert {
                    it[id] = team.id
                    it[name] = team.name
                }
            }
            println("TEAMS CREATED")
            agentsList.forEach { agent ->
                Agent.insert {
                    it[id] = agent.id
                    it[name] = agent.name
                    it[description] = agent.description
                    it[image] = agent.image
                    it[teamId] = agent.teamId
                }
            }

            println("AGENTS CREATED")
            cratesList.forEach { crate ->
                Crate.insert {
                    it[id] = crate.id
                    it[name] = crate.name
                    it[description] = crate.description
                    it[image] = crate.image
                }
            }

            println("CRATES CREATED")
            skinsList.forEach { skin ->
                Skin.insert {
                    it[id] = skin.id
                    it[name] = skin.name
                    it[description] = skin.description
                    it[image] = skin.image
                    it[teamId] = skin.team.id
                }

                val skinIdAndCratesId = skin.skinIdAndCratesId
                if (skinIdAndCratesId.isNotEmpty()) {
                    skinIdAndCratesId.forEach { relation ->
                        SkinCrates.insert {
                            it[skinId] = relation.skinId
                            it[crateId] = relation.crateId
                        }
                    }
                }
            }

            println("SKINS CREATED")
            keysList.forEach { key ->
                Key.insert {
                    it[id] = key.id
                    it[name] = key.name
                    it[description] = key.description
                    it[image] = key.image
                }

                val keyIdAndCratesId = key.keyIdAndCratesId
                if (keyIdAndCratesId.isNotEmpty()) {
                    keyIdAndCratesId.forEach { relation ->
                        KeyCrates.insert {
                            it[keyId] = relation.keyId
                            it[crateId] = relation.crateId
                        }
                    }
                }
            }
        }
    }
}