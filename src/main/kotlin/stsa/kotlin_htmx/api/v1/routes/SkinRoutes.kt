package stsa.kotlin_htmx.api.v1.routes

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import stsa.kotlin_htmx.api.v1.responses.externalSource.CrateResponse
import stsa.kotlin_htmx.api.v1.responses.externalSource.SkinResponse
import stsa.kotlin_htmx.api.v1.responses.externalSource.TeamResponse
import stsa.kotlin_htmx.api.v1.services.SkinService

fun Route.skinRouting(service: SkinService) {
    route("api/v1/skins") {
        get {
            val list = service.getSkinsOnWhere(crate = "Kilowatt Case")

            val listResponse = list.map { i ->
                SkinResponse(
                    id = i.id,
                    name = i.name,
                    description = i.description,
                    image = i.image,
                    team = TeamResponse(i.team.id, i.team.name),
                    crates = i.crates.map { c ->
                        CrateResponse(
                            id = c.id,
                            name = c.name,
                            description = c.description,
                            image = c.image
                        )
                    }
                )
            }

            if (listResponse.isNotEmpty()) {
                call.respond(
                    status = HttpStatusCode.OK,
                    message = listResponse
                )
            }
        }
    }
}