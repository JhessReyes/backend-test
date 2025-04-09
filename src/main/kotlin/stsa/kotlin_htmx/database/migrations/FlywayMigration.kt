package stsa.kotlin_htmx.database.migrations

import org.flywaydb.core.Flyway

object FlywayMigrations {
    fun migrate() {
        val dbUser = System.getenv("DB_USER")
        val dbPassword = System.getenv("DB_PASSWORD")
        val dbUrl = System.getenv("DB_URL")

        val flyway = Flyway.configure()
            .dataSource(
                dbUrl,
                dbUser,
                dbPassword
            )
            .locations("classpath:db/migration")
            .validateMigrationNaming(true)
            .load()
        flyway.migrate()

        println("MIGRATIONS INFO:")
        flyway.info().all().forEach {
            println("${it.version} - ${it.description} - ${it.state}")
        }
    }
}