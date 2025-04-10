package stsa.kotlin_htmx.database.repositories.impl

import org.jetbrains.exposed.sql.orWhere
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import stsa.kotlin_htmx.database.models.*
import stsa.kotlin_htmx.database.repositories.KeyRepository
import stsa.kotlin_htmx.database.repositories.WhereKey

class KeyDataSource(private val models: DatabaseModels) : KeyRepository {
    override suspend fun getKeys(where: WhereKey): List<KeyModel> {
        val keys = transaction {
            val query = (models.Key leftJoin models.KeyCrates leftJoin models.Crate)
                .selectAll()
                .apply {
                    if (where.id != null) {
                        orWhere { models.Key.id eq where.id }
                    }
                    if (where.name != null) {
                        orWhere { models.Key.name like "%${where.name}%" }
                    }
                    if (where.description != null) {
                        orWhere { models.Key.description like "%${where.description}%" }
                    }
                    if (where.image != null) {
                        orWhere { models.Key.image like "%${where.image}%" }
                    }
                    if (where.crateName != null) {
                        orWhere { models.Crate.name like "%${where.crateName}%" }
                    }
                }.groupBy(models.Key.id, models.KeyCrates.keyId, models.KeyCrates.crateId, models.Crate.id).toList()

            query.map { row ->
                val crates = listOf(
                    CrateModel(
                        id = row[models.Crate.id],
                        name = row[models.Crate.name],
                        description = row[models.Crate.description],
                        image = row[models.Crate.image]
                    )
                )
                KeyModel(
                    id = row[models.Key.id],
                    name = row[models.Key.name],
                    description = row[models.Key.description],
                    image = row[models.Key.image],
                    crates = crates
                )
            }

        }

        return keys
    }
}