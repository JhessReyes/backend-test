package stsa.kotlin_htmx.database.models

import org.jetbrains.exposed.sql.Table

object User : Table("tb_user") {
    val id = uuid("id")
    val name = varchar("name", 255)
    val email = varchar("email", 255)
    val password = varchar("password", 255)
    val salt = varchar("salt", 255)

    override val primaryKey = PrimaryKey(id)
}

data class UserModel(
    val id: String,
    val name: String,
    val email: String,
    val password: String,
    val salt: String
)