package stsa.kotlin_htmx.database.models

import org.jetbrains.exposed.sql.Table

object KeyCrates : Table("tb_key_crate") {
    val keyId = varchar("keyId", 255).references(Key.id)
    val crateId = varchar("crateId", 255).references(Crate.id)

    override val primaryKey = PrimaryKey(keyId, crateId)
}
