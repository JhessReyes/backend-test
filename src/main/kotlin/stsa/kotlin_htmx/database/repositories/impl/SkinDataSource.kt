package stsa.kotlin_htmx.database.repositories.impl

import org.jetbrains.exposed.sql.orWhere
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import stsa.kotlin_htmx.database.models.*
import stsa.kotlin_htmx.database.repositories.SkinRepository
import stsa.kotlin_htmx.database.repositories.WhereSkin

class SkinDataSource(skin: Skin) : SkinRepository {
    override suspend fun getSkins(where: WhereSkin): List<SkinModel> {
        val list = transaction {
            val query = (Skin leftJoin SkinCrates leftJoin Crate innerJoin Team)
                .selectAll()
                .orWhere {
                    Crate.name like "%${where.crate}%"
                }
                .groupBy(Skin.id, SkinCrates.skinId, SkinCrates.crateId, Crate.id, Team.id)
                .toList()

            query.map { row ->
                val crates = listOf(
                    CrateModel(
                        id = row[Crate.id],
                        name = row[Crate.name],
                        description = row[Crate.description],
                        image = row[Crate.image]
                    )
                )
                SkinModel(
                    id = row[Skin.id],
                    name = row[Skin.name],
                    description = row[Skin.description],
                    image = row[Skin.image],
                    team = TeamModel(id = row[Skin.teamId], name = row[Team.name]),
                    crates = crates
                )
            }
        }
        return list
    }
}