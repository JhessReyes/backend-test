package stsa.kotlin_htmx.api.v1.services

import java.util.concurrent.TimeUnit
import stsa.kotlin_htmx.api.v1.responses.externalSource.CrateResponse
import stsa.kotlin_htmx.api.v1.responses.externalSource.SkinResponse
import stsa.kotlin_htmx.api.v1.responses.externalSource.TeamResponse
import stsa.kotlin_htmx.database.models.SkinModel
import stsa.kotlin_htmx.database.repositories.WhereSkin
import stsa.kotlin_htmx.database.repositories.impl.SkinDataSource

class SkinService(private val repository: SkinDataSource) {
    private val skinCache = mutableMapOf<String, List<SkinModel>>()
    private val cacheTimestamps = mutableMapOf<String, Long>()
    private val cacheTTL = TimeUnit.MINUTES.toMillis(10)

    suspend fun getSkinsOnWhere(crate: String?): List<SkinResponse>? {
        val key = "skins_$crate"
        val now = System.currentTimeMillis()
        val isExpired = cacheTimestamps[key]?.let { now - it > cacheTTL } ?: true

        val cachedSkins = if (skinCache.containsKey(key) && !isExpired) {
            skinCache[key]!!
        } else {
            val skins = repository.getSkins(WhereSkin(crate))
            skinCache[key] = skins
            cacheTimestamps[key] = now
            skins
        }

        return cachedSkins.map { i ->
            SkinResponse(
                id = i.id,
                name = i.name,
                description = i.description,
                image = i.image,
                team = TeamResponse(i.team.id, i.team.name),
                crates =
                    i.crates.map { c ->
                        CrateResponse(
                            id = c.id,
                            name = c.name,
                            description = c.description,
                            image = c.image
                        )
                    }
            )
        }
    }
}
