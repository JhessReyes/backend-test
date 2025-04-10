package stsa.kotlin_htmx.api.v1.services

import java.util.concurrent.TimeUnit
import stsa.kotlin_htmx.api.v1.responses.externalSource.CrateResponse
import stsa.kotlin_htmx.api.v1.responses.externalSource.SkinResponse
import stsa.kotlin_htmx.api.v1.responses.externalSource.TeamResponse
import stsa.kotlin_htmx.api.v1.utils.CacheManager
import stsa.kotlin_htmx.database.models.SkinModel
import stsa.kotlin_htmx.database.repositories.WhereSkin
import stsa.kotlin_htmx.database.repositories.impl.SkinDataSource

class SkinService(private val repository: SkinDataSource) {
    private val skinCache = CacheManager<String, List<SkinModel>>(
        ttlMillis = TimeUnit.MINUTES.toMillis(10)
    )

    suspend fun getSkinsOnWhere(crate: String?): List<SkinResponse>? {
        val key = if (crate.isNullOrBlank()) "skins_all" else "skins_${crate}"
        val cachedSkins = skinCache.get(key) ?: run {
            val skins = repository.getSkins(WhereSkin(crate))
            skinCache.put(key, skins)
            skins
        }

        return cachedSkins.map { i ->
            SkinResponse(
                id = i.id,
                name = i.name,
                description = i.description,
                image = i.image,
                team = TeamResponse(i.team.id, i.team.name),
                crates = i.crates.map { c ->
                    CrateResponse(
                        id = c.id, name = c.name, description = c.description, image = c.image
                    )
                })
        }
    }
}
