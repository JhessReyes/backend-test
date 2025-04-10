package stsa.kotlin_htmx.database.models

class DatabaseModels(
    agent: Agent,
    skin: Skin,
    crate: Crate,
    key: Key,
    team: Team,
    keyCrates: KeyCrates,
    skinCrates: SkinCrates
) {
    val Agent: Agent = agent
    val Skin: Skin = skin
    val Crate: Crate = crate
    val Key: Key = key
    val Team: Team = team
    val KeyCrates: KeyCrates = keyCrates
    val SkinCrates: SkinCrates = skinCrates
}