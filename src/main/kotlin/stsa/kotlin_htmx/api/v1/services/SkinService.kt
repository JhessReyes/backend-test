package stsa.kotlin_htmx.api.v1.services

import stsa.kotlin_htmx.database.models.SkinModel
import stsa.kotlin_htmx.database.repositories.WhereSkin
import stsa.kotlin_htmx.database.repositories.impl.SkinDataSource

class SkinService(
    private val repository: SkinDataSource
) {
    suspend fun getSkinsOnWhere(crate: String?): List<SkinModel> {
        return repository.getSkins(WhereSkin(crate))
    }
}