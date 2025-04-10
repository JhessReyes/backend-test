package stsa.kotlin_htmx.database.repositories.impl

import org.jetbrains.exposed.sql.orWhere
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import stsa.kotlin_htmx.database.models.AgentModel
import stsa.kotlin_htmx.database.models.DatabaseModels
import stsa.kotlin_htmx.database.models.Team
import stsa.kotlin_htmx.database.models.TeamModel
import stsa.kotlin_htmx.database.repositories.AgentRepository
import stsa.kotlin_htmx.database.repositories.WhereAgent

class AgentDataSource(private val models: DatabaseModels) : AgentRepository {
    override suspend fun getAgents(where: WhereAgent): List<AgentModel> {
        val agents = transaction {
            val query = (models.Agent innerJoin models.Team)
                .selectAll()
                .apply {
                    if (where.id != null) {
                        orWhere { models.Agent.id eq where.id }
                    }
                    if (where.name != null) {
                        orWhere { models.Agent.name like "%${where.name}%" }
                    }
                    if (where.description != null) {
                        orWhere { models.Agent.description like "%${where.description}%" }
                    }
                    if (where.image != null) {
                        orWhere { models.Agent.image like "%${where.image}%" }
                    }
                    if (where.teamName != null) {
                        orWhere { models.Team.name like "%${where.teamName}%" }
                    }
                }.groupBy(models.Agent.id, models.Team.id).toList()

            query.map { row ->
                AgentModel(
                    id = row[models.Agent.id],
                    name = row[models.Agent.name],
                    description = row[models.Agent.description],
                    image = row[models.Agent.image],
                    team = TeamModel(id = row[models.Team.id], name = row[models.Team.name])
                )
            }

        }

        return agents
    }
}