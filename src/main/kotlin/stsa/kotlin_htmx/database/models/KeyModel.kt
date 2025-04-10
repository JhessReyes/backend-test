package stsa.kotlin_htmx.database.models

import org.jetbrains.exposed.sql.Table

object Key : Table("tb_key") {
    val id = varchar("id", 255)
    val name = varchar("name", 255)
    val description = text("description")
    val image = varchar("image", 255)

    override val primaryKey = PrimaryKey(id)
}

data class KeyModel(
    val id: String,
    val name: String,
    val description: String,
    val image: String,
    val crates: List<CrateModel>
)