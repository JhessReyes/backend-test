package stsa.kotlin_htmx.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sse.SSE
import org.slf4j.LoggerFactory
import stsa.kotlin_htmx.api.v1.routes.*
import stsa.kotlin_htmx.api.v1.services.AgentService
import stsa.kotlin_htmx.api.v1.services.CrateService
import stsa.kotlin_htmx.api.v1.services.KeyService
import stsa.kotlin_htmx.api.v1.services.SkinService
import stsa.kotlin_htmx.database.models.*
import stsa.kotlin_htmx.database.repositories.SkinRepository
import stsa.kotlin_htmx.database.repositories.impl.AgentDataSource
import stsa.kotlin_htmx.database.repositories.impl.CrateDataSource
import stsa.kotlin_htmx.database.repositories.impl.KeyDataSource
import stsa.kotlin_htmx.database.repositories.impl.SkinDataSource

fun Application.configureRouting() {
    val logger = LoggerFactory.getLogger("Routing")
    install(SSE)
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            logger.error("Unhandled error", cause)
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        staticResources("/static", "static")
        val models = DatabaseModels(Agent, Skin, Crate, Key, Team, KeyCrates, SkinCrates)
        route("api/v1") {
            // skin routing
            val skinRepo = SkinDataSource(models)
            val skinService = SkinService(skinRepo)
            skinRouting(skinService)

            // agent routing
            val agentDataSource = AgentDataSource(models)
            val agentService = AgentService(agentDataSource)
            agentRouting(agentService)

            // crate routing
            val crateDataSource = CrateDataSource(models)
            val crateService = CrateService(crateDataSource)
            crateRouting(crateService)

            // key routing
            val keyDataSource = KeyDataSource(models)
            val keyService = KeyService(keyDataSource)
            keyRouting(keyService)

            userRouting()

        }
    }
}
