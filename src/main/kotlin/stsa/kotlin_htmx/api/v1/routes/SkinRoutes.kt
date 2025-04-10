package stsa.kotlin_htmx.api.v1.routes

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import stsa.kotlin_htmx.api.v1.services.SkinService
import stsa.kotlin_htmx.api.v1.utils.XmlUtils

fun Route.skinRouting(service: SkinService) {
    route("api/v1/skins") {
        get {
            val crate = call.request.queryParameters["crates"]
            val skins = service.getSkinsOnWhere(crate = crate)
            if (skins.isNotEmpty()) {
                call.respond(
                    status = HttpStatusCode.OK,
                    message = skins
                )
            } else return@get
        }
        get("/xml") {
            val crate = call.request.queryParameters["crates"]
            val skins = service.getSkinsOnWhere(crate = crate)
            val xml = XmlUtils.skinsToXml(skins)

            if (skins.isNotEmpty()) {
                call.respond(
                    status = HttpStatusCode.OK,
                    message = call.respondText(xml, ContentType.Application.Xml)
                )
            } else return@get
        }
    }
}