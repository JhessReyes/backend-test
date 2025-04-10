package stsa.kotlin_htmx.api.v1.routes

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import stsa.kotlin_htmx.api.v1.services.SkinService
import stsa.kotlin_htmx.api.v1.utils.XmlUtils
import stsa.kotlin_htmx.database.repositories.WhereSkin

fun Route.skinRouting(service: SkinService) {
    route("/skins") {
        get {
            val getParam = call.request.queryParameters
            val whereSkin =
                WhereSkin(
                    id = getParam["id"],
                    name = getParam["name"],
                    description = getParam["description"],
                    image = getParam["image"],
                    teamName = getParam["teamName"],
                    crateName = getParam["crateName"]
                )
            val skins = service.getSkinsOnWhere(whereSkin)

            if (skins?.isNotEmpty() == true) {
                call.respond(
                    status = HttpStatusCode.OK,
                    message = skins
                )
            } else return@get

        }
        get("/xml") {
            val getParam = call.request.queryParameters
            val whereSkin =
                WhereSkin(
                    id = getParam["id"],
                    name = getParam["name"],
                    description = getParam["description"],
                    image = getParam["image"],
                    teamName = getParam["teamName"],
                    crateName = getParam["crateName"]
                )
            val skins = service.getSkinsOnWhere(whereSkin)
            val xml = skins?.let { XmlUtils.skinsToXml(it) }

            if (xml != null) {
                call.respond(
                    status = HttpStatusCode.OK,
                    message = call.respondText(xml, ContentType.Application.Xml)
                )
            } else return@get
        }
    }
}