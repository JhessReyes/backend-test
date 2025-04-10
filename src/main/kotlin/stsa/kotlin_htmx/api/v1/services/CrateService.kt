package stsa.kotlin_htmx.api.v1.services

import stsa.kotlin_htmx.api.v1.responses.externalSource.CrateResponse
import stsa.kotlin_htmx.api.v1.utils.CacheManager
import stsa.kotlin_htmx.database.models.CrateModel
import stsa.kotlin_htmx.database.repositories.CrateRepository
import stsa.kotlin_htmx.database.repositories.WhereCrate
import stsa.kotlin_htmx.database.repositories.WhereSkin
import java.util.concurrent.TimeUnit

class CrateService(private val repository: CrateRepository) {
    private val crateCache = CacheManager<String, List<CrateModel>>(
        ttlMillis = TimeUnit.MINUTES.toMillis(10)
    )

    suspend fun getCratesOnWhere(whereCrate: WhereCrate): List<CrateResponse> {
        val filters = mapOf(
            "id" to whereCrate.id,
            "name" to whereCrate.name,
            "description" to whereCrate.description,
            "image" to whereCrate.image
        )
        val key = crateCache.generateKey("crates", filters = filters)

        val cachedCrates = crateCache.get(key) ?: run {
            val crates = repository.getCrates(whereCrate)
            crateCache.put(key, crates)
            crates
        }

        return cachedCrates.map { i ->
            CrateResponse(
                id = i.id,
                name = i.name,
                description = i.description,
                image = i.image,
            )
        }
    }
}