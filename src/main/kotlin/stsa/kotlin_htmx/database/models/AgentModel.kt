package stsa.kotlin_htmx.database.models

import org.jetbrains.exposed.sql.Table

object Agent : Table("tb_agent") {
    val id = varchar("id", 255)
    val name = varchar("name", 255)
    val description = text("description")
    val image = varchar("image", 255)
    val teamId = varchar("teamId", 255).references(Team.id)

    override val primaryKey = PrimaryKey(id)
}

data class AgentModel(
    val id: String,
    val name: String,
    val description: String,
    val image: String,
    val team: TeamModel
)