package stsa.kotlin_htmx.database.repositories.impl

import org.jetbrains.exposed.sql.orWhere
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import stsa.kotlin_htmx.database.models.*
import stsa.kotlin_htmx.database.repositories.SkinRepository
import stsa.kotlin_htmx.database.repositories.WhereSkin

class SkinDataSource(private val models: DatabaseModels) : SkinRepository {
    override suspend fun getSkins(where: WhereSkin): List<SkinModel> {
        val list = transaction {
            val query = (models.Skin leftJoin models.SkinCrates leftJoin models.Crate innerJoin models.Team)
                .selectAll()
                .apply {
                    if (where.id != null) {
                        orWhere { models.Skin.id eq where.id }
                    }
                    if (where.name != null) {
                        orWhere { models.Skin.name like "%${where.name}%" }
                    }
                    if (where.description != null) {
                        orWhere { models.Skin.description like "%${where.description}%" }
                    }
                    if (where.image != null) {
                        orWhere { models.Skin.image like "%${where.image}%" }
                    }
                    if (where.teamName != null) {
                        orWhere { models.Team.name like "%${where.teamName}%" }
                    }
                    if (where.crateName != null) {
                        orWhere { models.Crate.name like "%${where.crateName}%" }
                    }
                }
                .groupBy(
                    models.Skin.id, models.SkinCrates.skinId, models.SkinCrates.crateId, models.Crate.id, models.Team.id
                )
                .toList()

            query.map { row ->
                val crates = row[models.Crate.id]?.let { crateId ->
                    listOf(
                        CrateModel(
                            id = crateId,
                            name = row[models.Crate.name],
                            description = row[models.Crate.description],
                            image = row[models.Crate.image]
                        )
                    )
                } ?: emptyList()

                SkinModel(
                    id = row[models.Skin.id],
                    name = row[models.Skin.name],
                    description = row[models.Skin.description],
                    image = row[models.Skin.image],
                    team = TeamModel(id = row[models.Team.id], name = row[models.Team.name]),
                    crates = crates
                )
            }
        }
        return list
    }
}