package com.macste.scoreboard.storage

import com.macste.scoreboard.db.exposed.models.GameDAO
import com.macste.scoreboard.db.exposed.models.GameTable
import com.macste.scoreboard.domain.models.Game
import org.jetbrains.exposed.dao.load
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component("databaseGameStorage")
class DatabaseGameStorage : SimpleStorage<Game> {
    private val logger = LoggerFactory.getLogger(DatabaseGameStorage::class.java)
    override fun add(item: Set<Game>): List<Game?> {
        return item.map { tryAddGame(it)?.asDomainModel() }
    }

    override fun add(item: Game): Game? = tryAddGame(item)?.asDomainModel()

    override fun getAll(): Set<Game> = transaction { GameDAO.all().map { it.asDomainModel() } }.toSet()

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