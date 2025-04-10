package stsa.kotlin_htmx.api.v1.responses.externalSource

import kotlinx.serialization.Serializable

@Serializable
data class KeyResponse(
    val id: String,
    val name: String,
    val description: String,
    val image: String,
    val crates: List<CrateResponse>
) {
    val keyIdAndCratesId: List<Map<String, String>>
        get() = crates.map { crate ->
            mapOf(
                "crateId" to crate.id,
                "keyId" to this.id
            )
        }
}
