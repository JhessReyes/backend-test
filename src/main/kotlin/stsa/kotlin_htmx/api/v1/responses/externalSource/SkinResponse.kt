package stsa.kotlin_htmx.api.v1.responses.externalSource

import kotlinx.serialization.Serializable

@Serializable
data class SkinResponse(
    val id: String,
    val name: String,
    val description: String,
    val image: String,
    val team: TeamResponse,
    val crates: List<CrateResponse>
) {
    val skinIdAndCratesId: List<Map<String, String>>
        get() = crates.map { crate ->
            mapOf(
                "crateId" to crate.id,
                "skinId" to this.id
            )
        }
}
