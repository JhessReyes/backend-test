package stsa.kotlin_htmx.api.v1.routes

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import stsa.kotlin_htmx.api.v1.responses.UserResponse

private val users: Collection<UserResponse> = mutableListOf(
    UserResponse("671a0781-7c7f-4e50-9703-fd032fdaafca", "Jhonatan Reyes", "jessaureyes@gmail.com", "pass", "salt")
)

fun Route.userRouting() {
    route("api/v1/users")
    {
        get {
            if (users.isNotEmpty()) {
                call.respond(
                    status = HttpStatusCode.OK,
                    message = users.toList()
                )
            }
        }
    }
}