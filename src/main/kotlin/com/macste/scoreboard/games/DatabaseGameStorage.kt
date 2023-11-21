package com.macste.scoreboard.games

import com.macste.scoreboard.db.exposed.models.GameDAO
import com.macste.scoreboard.db.exposed.models.GameTable
import com.macste.scoreboard.domain.models.Game
import org.jetbrains.exposed.dao.load
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component("databaseGameStorage")
class DatabaseGameStorage : GameStorage {
    private val logger = LoggerFactory.getLogger(DatabaseGameStorage::class.java)
    override fun addGames(games: Set<Game>): List<Game?> {
        return games.map { tryAddGame(it)?.asDomainModel() }
    }

    override fun addGame(game: Game): Game? = tryAddGame(game)?.asDomainModel()

    override fun getAllGames(): Set<Game> = transaction { GameDAO.all().map { it.asDomainModel() } }.toSet()

    override fun clearAll() {
        transaction { GameTable.deleteAll() }
    }

    private fun tryAddGame(game: Game): GameDAO? = try {
        transaction { GameDAO.new { title = game.title }.load() }
    } catch (ex: Exception) {
        logger.error("Error while trying to add new game: ${ex.message}")
        null
    }
}