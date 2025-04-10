package stsa.kotlin_htmx.database.repositories

import stsa.kotlin_htmx.database.models.SkinModel

data class WhereSkin(
    val crate: String? = null
)

interface SkinRepository {
    suspend fun getSkins(where: WhereSkin): List<SkinModel>
}