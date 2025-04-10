package stsa.kotlin_htmx

import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            module()
        }
        client.get("/api/v1/skins").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
        client.get("/api/v1/agents").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
        client.get("/api/v1/crates").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
        client.get("/api/v1/keys").apply {
            assertEquals(HttpStatusCode.Unauthorized, status)
        }
        //TODO: <-- YOUR CODE HERE -> Use an authenticated client
        val authenticatedClient = createClient {
            install(Auth) {
                basic {
                    credentials {
                        BasicAuthCredentials(username = "admin", password = "secret")
                    }
                }
            }
        }
        authenticatedClient.get("/api/v1/keys").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }
}
