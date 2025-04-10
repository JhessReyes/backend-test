package stsa.kotlin_htmx.database.models

import org.jetbrains.exposed.sql.Table

object Skin : Table("tb_skin") {
    val id = varchar("id", 255)
    val name = varchar("name", 255)
    val description = text("description")
    val image = varchar("image", 255)
    val teamId = varchar("teamId", 255).references(Team.id)

    override val primaryKey = PrimaryKey(id)
}

data class SkinModel(
    val id: String,
    val name: String,
    val description: String,
    val image: String,
    val team: TeamModel,
    val crates: List<CrateModel>
)
