package stsa.kotlin_htmx.database.models

import org.jetbrains.exposed.sql.Table

object Crate : Table("tb_crate") {
    val id = varchar("id", 255)
    val name = varchar("name", 255)
    val description = text("description").nullable()
    val image = varchar("image", 255)

    override val primaryKey = PrimaryKey(id)
}

data class CrateModel(
    val id: String,
    val name: String,
    val description: String? = null,
    val image: String
)