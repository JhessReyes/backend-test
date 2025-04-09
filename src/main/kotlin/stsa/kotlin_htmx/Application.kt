package stsa.kotlin_htmx

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.compression.*
import stsa.kotlin_htmx.database.DatabaseFactory
import stsa.kotlin_htmx.database.migrations.FlywayMigrations
import stsa.kotlin_htmx.plugins.configureHTTP
import stsa.kotlin_htmx.plugins.configureMonitoring
import stsa.kotlin_htmx.plugins.configureRouting
import stsa.kotlin_htmx.plugins.configureSerialization
import java.io.File

data class ApplicationConfig(
    val lookupApiKey: String
) {

    companion object {
        fun load(): ApplicationConfig {
            System.setProperty("io.ktor.development", "true")

            fun Map<String, String>.envOrLookup(key: String): String {
                return System.getenv(key) ?: this[key]!!
            }

            val envVars: Map<String, String> = envFile().let { envFile ->
                if (envFile.exists()) {
                    envFile.readLines()
                        .map { it.split("=") }
                        .filter { it.size == 2 }
                        .associate { it.first().trim() to it.last().trim() }
                } else emptyMap()
            }

            return ApplicationConfig(
                lookupApiKey = envVars.envOrLookup("LOOKUP_API_KEY")
            )
        }

    }
}

fun envFile(): File {
    // I don't really recommend having this default env file, but do it now to ease testing of example app
    // Settings in ENV will override file always
    return listOf(".env.local", ".env.default").map { File(it) }.first { it.exists() }
}

fun main() {
    // Have to do this before the rest of the loading of KTor. I guess it's because it does something fancy
    // with the classloader to be able to do hot reload.
    if (envFile().readText().contains("KTOR_DEVELOPMENT=true")) System.setProperty(
        "io.ktor.development",
        "true"
    )
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module).start(wait = true)
}

fun Application.module() {
    FlywayMigrations.migrate()
    /* DatabaseFactory.init()*/
    configureSerialization()
    configureHTTP()
    configureMonitoring()
    configureRouting()
    install(Compression)
    // Manual dependency injection :) Usually smart to find a separate place to do this from KTor
    val config = ApplicationConfig.load()

    // Load pages
    configurePageRoutes()
}
