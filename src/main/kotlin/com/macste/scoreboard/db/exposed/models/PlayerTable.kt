package com.macste.scoreboard.db.exposed.models

import com.macste.scoreboard.domain.models.DomainModelConvertible
import com.macste.scoreboard.domain.models.Player
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object PlayerTable : IntIdTable(name = "player") {
    val nickname = varchar("nickname", 127).uniqueIndex()
}

class PlayerDAO(id: EntityID<Int>) : IntEntity(id), DomainModelConvertible<Player> {
    companion object : IntEntityClass<PlayerDAO>(PlayerTable)

    var nickname by PlayerTable.nickname

    override fun asDomainModel(): Player = Player(nickname)
}