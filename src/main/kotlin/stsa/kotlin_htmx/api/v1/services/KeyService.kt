package stsa.kotlin_htmx.api.v1.services

import stsa.kotlin_htmx.api.v1.responses.externalSource.CrateResponse
import stsa.kotlin_htmx.api.v1.responses.externalSource.KeyResponse
import stsa.kotlin_htmx.api.v1.utils.CacheManager
import stsa.kotlin_htmx.database.models.KeyModel
import stsa.kotlin_htmx.database.repositories.KeyRepository
import stsa.kotlin_htmx.database.repositories.WhereKey
import java.util.concurrent.TimeUnit

class KeyService(private val repository: KeyRepository) {
    private val keyCache = CacheManager<String, List<KeyModel>>(
        ttlMillis = TimeUnit.MINUTES.toMillis(10)
    )

    suspend fun getKeysOnWhere(whereKey: WhereKey): List<KeyResponse> {
        val filters = mapOf(
            "id" to whereKey.id,
            "name" to whereKey.name,
            "description" to whereKey.description,
            "image" to whereKey.image,
            "crateName" to whereKey.crateName
        )
        val key = keyCache.generateKey("keys", filters = filters)

        val cachedKeys = keyCache.get(key) ?: run {
            val keys = repository.getKeys(whereKey)
            keyCache.put(key, keys)
            keys
        }

        return cachedKeys.map { i ->
            KeyResponse(
                id = i.id,
                name = i.name,
                description = i.description,
                image = i.image,
                crates = i.crates.map { c ->
                    CrateResponse(
                        id = c.id, name = c.name, description = c.description, image = c.image
                    )
                }
            )
        }
    }
}