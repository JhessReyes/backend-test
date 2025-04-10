package stsa.kotlin_htmx.database.models

import org.jetbrains.exposed.sql.Table

object Skin : Table("tb_skin") {
    val id = varchar("id", 255)
    val name = varchar("name", 255)
    val description = varchar("description", 255)
    val image = varchar("image", 255)
    val teamId = varchar("teamId", 255)

    override val primaryKey = PrimaryKey(id)
}