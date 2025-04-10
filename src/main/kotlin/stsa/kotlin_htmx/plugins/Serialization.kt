package stsa.kotlin_htmx.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}
