package stsa.kotlin_htmx.database.repositories.impl

import org.jetbrains.exposed.sql.orWhere
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import stsa.kotlin_htmx.database.models.*
import stsa.kotlin_htmx.database.repositories.CrateRepository
import stsa.kotlin_htmx.database.repositories.WhereCrate

class CrateDataSource(private val models: DatabaseModels) : CrateRepository {
    override suspend fun getCrates(where: WhereCrate): List<CrateModel> {
        val agents = transaction {
            val query = (models.Crate)
                .selectAll()
                .apply {
                    if (where.id != null) {
                        orWhere { models.Crate.id eq where.id }
                    }
                    if (where.name != null) {
                        orWhere { models.Crate.name like "%${where.name}%" }
                    }
                    if (where.description != null) {
                        orWhere { models.Crate.description like "%${where.description}%" }
                    }
                    if (where.image != null) {
                        orWhere { models.Crate.image like "%${where.image}%" }
                    }
                }.toList()

            query.map { row ->
                CrateModel(
                    id = row[models.Crate.id],
                    name = row[models.Crate.name],
                    description = row[models.Crate.description],
                    image = row[models.Crate.image]
                )
            }

        }

        return agents
    }
}