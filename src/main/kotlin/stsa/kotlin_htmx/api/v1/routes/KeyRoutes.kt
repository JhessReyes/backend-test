package stsa.kotlin_htmx.api.v1.routes

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import stsa.kotlin_htmx.api.v1.services.KeyService
import stsa.kotlin_htmx.database.repositories.WhereKey

fun Route.keyRouting(service: KeyService) {
    route("/keys") {
        get {
            val getParam = call.request.queryParameters
            val whereKey =
                WhereKey(
                    id = getParam["id"],
                    name = getParam["name"],
                    description = getParam["description"],
                    image = getParam["image"],
                    crateName = getParam["crateName"]
                )
            val keys = service.getKeysOnWhere(whereKey)

            if (keys.isNotEmpty() == true) {
                call.respond(
                    status = HttpStatusCode.OK,
                    message = keys
                )
            } else return@get
        }
    }
}