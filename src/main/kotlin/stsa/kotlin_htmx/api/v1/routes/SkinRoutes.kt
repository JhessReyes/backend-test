package stsa.kotlin_htmx.api.v1.routes

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import stsa.kotlin_htmx.database.models.Skin

fun Route.skinRouting() {
    route("api/v1/skins") {
        get {
            val list = transaction {
                Skin.selectAll().map {
                    mapOf(
                        "id" to it[Skin.id].toString(),
                        "name" to it[Skin.name],
                        "description" to it[Skin.description]
                    )
                }
            }

            if (list.isNotEmpty()) {
                call.respond(
                    status = HttpStatusCode.OK,
                    message = list
                )
            }
        }
    }
}