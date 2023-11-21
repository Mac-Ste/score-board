package com.macste.scoreboard.db.exposed.models

import com.macste.scoreboard.domain.models.DomainModelConvertible
import com.macste.scoreboard.domain.models.Game
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object GameTable : IntIdTable(name = "game") {
    val title = varchar("title", 255).uniqueIndex()
}

class GameDAO(id: EntityID<Int>) : IntEntity(id), DomainModelConvertible<Game> {
    companion object : IntEntityClass<GameDAO>(GameTable)

    var title by GameTable.title
    override fun asDomainModel(): Game = Game(title)
}