package stsa.kotlin_htmx.database.models

import org.jetbrains.exposed.sql.Table

object Team : Table("tb_team") {
    val id = varchar("id", 255)
    val name = varchar("name", 255)

    override val primaryKey = PrimaryKey(id)
}

data class TeamModel(
    val id: String,
    val name: String
)