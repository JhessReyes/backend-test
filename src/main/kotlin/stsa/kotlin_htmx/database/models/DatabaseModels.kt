package stsa.kotlin_htmx.database.models

class DatabaseModels(
    agentModel: Agent,
    skinModel: Skin,
    crateModel: Crate,
    keyModel: Key,
    teamModel: Team
) {
    val Agent: Agent = agentModel
    val Skin: Skin = skinModel
    val Crate: Crate = crateModel
    val Key: Key = keyModel
    val Team: Team = teamModel
}