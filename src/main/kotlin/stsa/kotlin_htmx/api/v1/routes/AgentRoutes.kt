package stsa.kotlin_htmx.api.v1.routes

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import stsa.kotlin_htmx.api.v1.services.AgentService
import stsa.kotlin_htmx.database.repositories.WhereAgent

fun Route.agentRouting(service: AgentService) {
    route("/agents") {
        get {
            val getParam = call.request.queryParameters
            val whereAgent =
                WhereAgent(
                    id = getParam["id"],
                    name = getParam["name"],
                    teamName = getParam["teamName"],
                    description = getParam["description"],
                    image = getParam["image"]
                )
            val agents = service.getAgentsOnWhere(whereAgent)

            if (agents.isNotEmpty() == true) {
                call.respond(
                    status = HttpStatusCode.OK,
                    message = agents
                )
            } else return@get
        }
    }
}