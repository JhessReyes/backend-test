package stsa.kotlin_htmx.api.v1.routes

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import stsa.kotlin_htmx.api.v1.services.CrateService
import stsa.kotlin_htmx.api.v1.utils.XmlUtils
import stsa.kotlin_htmx.database.repositories.WhereAgent
import stsa.kotlin_htmx.database.repositories.WhereCrate

fun Route.crateRouting(service: CrateService) {
    route("/crates") {
        get {
            val getParam = call.request.queryParameters
            val whereCrate =
                WhereCrate(
                    id = getParam["id"],
                    name = getParam["name"],
                    description = getParam["description"],
                    image = getParam["image"]
                )
            val crates = service.getCratesOnWhere(whereCrate)

            if (crates.isNotEmpty() == true) {
                call.respond(
                    status = HttpStatusCode.OK,
                    message = crates
                )
            } else return@get
        }

        get("/xml") {
            val getParam = call.request.queryParameters
            val whereCrate =
                WhereCrate(
                    id = getParam["id"],
                    name = getParam["name"],
                    description = getParam["description"],
                    image = getParam["image"]
                )
            val crates = service.getCratesOnWhere(whereCrate)
            val xml = crates.let { XmlUtils.cratesToXml(it) }

            if (xml.isNotEmpty()) {
                call.respond(
                    status = HttpStatusCode.OK,
                    message = call.respondText(xml, ContentType.Application.Xml)
                )
            } else return@get
        }
    }
}