package stsa.kotlin_htmx.api.v1.responses.externalSource

import kotlinx.serialization.Serializable

@Serializable
data class KeyCrateRelation(val keyId: String, val crateId: String)

@Serializable
data class KeyResponse(
    val id: String,
    val name: String,
    val description: String,
    val image: String,
    val crates: List<CrateResponse>
) {
    val keyIdAndCratesId: List<KeyCrateRelation>
        get() = crates.map { crate -> KeyCrateRelation(id, crate.id) }
}
