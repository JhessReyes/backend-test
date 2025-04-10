package stsa.kotlin_htmx.database.repositories

import stsa.kotlin_htmx.database.models.SkinModel

data class WhereSkin(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val image: String? = null,
    val teamName: String? = null,
    val crateName: String? = null
)

interface SkinRepository {
    suspend fun getSkins(where: WhereSkin): List<SkinModel>
}