package stsa.kotlin_htmx.api.v1.responses.externalSource

import kotlinx.serialization.Serializable

@Serializable
data class SkinCrateRelation(val skinId: String, val crateId: String)

@Serializable
data class SkinResponse(
    val id: String,
    val name: String,
    val description: String,
    val image: String,
    val team: TeamResponse,
    val crates: List<CrateResponse>
) {
    val skinIdAndCratesId: List<SkinCrateRelation>
        get() = crates.map { crate -> SkinCrateRelation(id, crate.id) }
}
