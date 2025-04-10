package stsa.kotlin_htmx.database.repositories

import stsa.kotlin_htmx.database.models.KeyModel

data class WhereKey(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val image: String? = null,
    val crateName: String? = null
)

interface KeyRepository {
    suspend fun getKeys(where: WhereKey): List<KeyModel>
}
