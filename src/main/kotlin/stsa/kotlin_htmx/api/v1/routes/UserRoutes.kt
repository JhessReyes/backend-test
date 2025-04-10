package stsa.kotlin_htmx.api.v1.routes

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import stsa.kotlin_htmx.database.models.User

fun Route.userRouting() {
    route("api/v1/users")
    {
        get {
            val list = transaction {
                User.selectAll().map {
                    mapOf(
                        "id" to it[User.id].toString(),
                        "name" to it[User.name],
                        "email" to it[User.email]
                    )
                }
            }

            if (list.isNotEmpty()) {
                call.respond(
                    status = HttpStatusCode.OK,
                    message = list
                )
            } else return@get
        }
    }
}