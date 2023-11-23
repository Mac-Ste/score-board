package com.macste.scoreboard.db.exposed.models

import com.macste.scoreboard.domain.models.DomainModelConvertible
import com.macste.scoreboard.domain.models.Match
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object MatchTable : IntIdTable(name = "match") {
    val game = reference("game", GameTable)
    val start = datetime("start").default(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()))
    val end = datetime("end").nullable()
    val status = enumeration("status", Match.Status::class).default(Match.Status.IN_PROGRESS)
}

class MatchDAO(id: EntityID<Int>) : IntEntity(id), DomainModelConvertible<Match> {
    companion object : IntEntityClass<MatchDAO>(MatchTable)

    var game by GameDAO referencedOn MatchTable.game
    var status by MatchTable.status
    var start by MatchTable.start
    var end by MatchTable.end

    override fun asDomainModel(): Match = Match(
        id = id.value,
        game = game.asDomainModel(),
        start = start.toInstant(TimeZone.currentSystemDefault()),
        end = end?.toInstant(TimeZone.currentSystemDefault()),
        status = status
    )
}