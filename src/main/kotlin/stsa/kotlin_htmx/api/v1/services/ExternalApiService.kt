package stsa.kotlin_htmx.api.v1.services

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json
import stsa.kotlin_htmx.api.v1.responses.externalSource.AgentResponse
import stsa.kotlin_htmx.api.v1.responses.externalSource.CrateResponse
import stsa.kotlin_htmx.api.v1.responses.externalSource.KeyResponse
import stsa.kotlin_htmx.api.v1.responses.externalSource.SkinResponse

private const val EXTERNAL_API = "https://raw.githubusercontent.com/ByMykel/CSGO-API/main/public/api/en"

class ExternalApiService(private val client: HttpClient) {
    private val jsonBuilder = Json { ignoreUnknownKeys = true }

    suspend fun getAgentsData(): List<AgentResponse> {
        val response: HttpResponse =
            client.get("$EXTERNAL_API/agents.json")
        val jsonText = response.bodyAsText()
        return jsonBuilder.decodeFromString<List<AgentResponse>>(jsonText)
    }

    suspend fun getSkinsData(): List<SkinResponse> {
        val response: HttpResponse =
            client.get("$EXTERNAL_API/skins.json")
        val jsonText = response.bodyAsText()
        return jsonBuilder.decodeFromString<List<SkinResponse>>(jsonText)
    }

    suspend fun getKeysData(): List<KeyResponse> {
        val response: HttpResponse =
            client.get("$EXTERNAL_API/keys.json")
        val jsonText = response.bodyAsText()
        return jsonBuilder.decodeFromString<List<KeyResponse>>(jsonText)
    }

    suspend fun getCratesData(): List<CrateResponse> {
        val response: HttpResponse =
            client.get("$EXTERNAL_API/crates.json")
        val jsonText = response.bodyAsText()
        return jsonBuilder.decodeFromString<List<CrateResponse>>(jsonText)
    }
}