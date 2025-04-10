package stsa.kotlin_htmx.database

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import stsa.kotlin_htmx.database.models.*

object DatabaseFactory {
    fun init() {
        val dbUser = System.getenv("DB_USER")
        val dbPassword = System.getenv("DB_PASSWORD")
        val dbUrl = System.getenv("DB_URL")
        val dbDriver = System.getenv("DB_DRIVER")

        Database.connect(
            url = dbUrl, driver = dbDriver, user = dbUser, password = dbPassword
        )

        transaction {
            SchemaUtils.create(Agent, Crate, KeyCrates, Key, SkinCrates, Skin, Team, Crate)
        }
    }
}