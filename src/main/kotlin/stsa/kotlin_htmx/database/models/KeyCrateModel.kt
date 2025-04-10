package stsa.kotlin_htmx.database.models

import org.jetbrains.exposed.sql.Table

object KeyCrates : Table("tb_key_crate") {
    val keyId = varchar("keyId", 255)
    val crateId = varchar("crateId", 255)

    override val primaryKey = PrimaryKey(keyId, crateId)
}
