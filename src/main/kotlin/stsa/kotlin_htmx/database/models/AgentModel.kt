package stsa.kotlin_htmx.database.models

import org.jetbrains.exposed.sql.Table

object Agent : Table("tb_agent") {
    val id = varchar("id", 255)
    val name = varchar("name", 255)
    val description = text("description")
    val image = varchar("image", 255)
    val teamId = varchar("teamId", 255)

    override val primaryKey = PrimaryKey(id)
}
