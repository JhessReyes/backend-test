package stsa.kotlin_htmx

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
        client.get("/api/v1//agents").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
        client.get("/api/v1//crates").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
        client.get("/api/v1//keys").apply {
            assertEquals(HttpStatusCode.Unauthorized, status)
        }
        //TODO: <-- YOUR CODE HERE -> Use an authenticated client
        client.get("/api/v1//keys").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }
}
