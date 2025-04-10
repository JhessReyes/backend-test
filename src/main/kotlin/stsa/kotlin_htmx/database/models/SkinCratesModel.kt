package stsa.kotlin_htmx.database.models

import org.jetbrains.exposed.sql.Table

object SkinCrates : Table("tb_skin_crate") {
    val skinId = varchar("skinId", 255)
    val crateId = varchar("crateId", 255)

    override val primaryKey = PrimaryKey(skinId, crateId)
}
