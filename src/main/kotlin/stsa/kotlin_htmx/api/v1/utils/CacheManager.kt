package stsa.kotlin_htmx.api.v1.utils


import java.util.concurrent.TimeUnit

class CacheEntry<T>(
    val data: T,
    val timestamp: Long
)

class CacheManager<K, V>(
    private val ttlMillis: Long = TimeUnit.MINUTES.toMillis(10)
) {
    private val cache = mutableMapOf<K, CacheEntry<V>>()

    fun get(key: K): V? {
        val entry = cache[key]
        val now = System.currentTimeMillis()
        return if (entry != null && now - entry.timestamp < ttlMillis) {
            entry.data
        } else {
            cache.remove(key)
            null
        }
    }

    fun put(key: K, value: V) {
        cache[key] = CacheEntry(value, System.currentTimeMillis())
    }

    fun invalidate(key: K) {
        cache.remove(key)
    }

    fun clear() {
        cache.clear()
    }
}
