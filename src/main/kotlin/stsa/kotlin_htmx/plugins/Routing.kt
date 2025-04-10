package stsa.kotlin_htmx.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sse.SSE
import org.slf4j.LoggerFactory
import stsa.kotlin_htmx.api.v1.routes.skinRouting
import stsa.kotlin_htmx.api.v1.routes.userRouting
import stsa.kotlin_htmx.api.v1.services.SkinService
import stsa.kotlin_htmx.database.models.Skin
import stsa.kotlin_htmx.database.repositories.SkinRepository
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
        //skin routing
        val skinRepo = SkinDataSource(Skin)
        val skinService = SkinService(skinRepo)
        skinRouting(skinService)

        userRouting()
    }
}
