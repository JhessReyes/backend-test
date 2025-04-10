package stsa.kotlin_htmx.database.repositories

import stsa.kotlin_htmx.database.models.CrateModel

data class WhereCrate(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val image: String? = null,
)

interface CrateRepository {
    suspend fun getCrates(where: WhereCrate): List<CrateModel>
}
