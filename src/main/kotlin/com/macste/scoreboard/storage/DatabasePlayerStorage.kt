package com.macste.scoreboard.storage

import com.macste.scoreboard.db.exposed.models.PlayerDAO
import com.macste.scoreboard.db.exposed.models.PlayerTable
import com.macste.scoreboard.domain.models.Player
import org.jetbrains.exposed.dao.load
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component("databasePlayerStorage")
class DatabasePlayerStorage : SimpleStorage<Player> {
    private val logger = LoggerFactory.getLogger(DatabasePlayerStorage::class.java)

    override fun add(item: Set<Player>): List<Player?> {
        return item.map { tryAddPlayer(it)?.asDomainModel() }
    }

    override fun add(item: Player): Player? = tryAddPlayer(item)?.asDomainModel()

    override fun getAll(): Set<Player> = transaction { PlayerDAO.all().map { it.asDomainModel() } }.toSet()

    override fun clearAll() {
        transaction { PlayerTable.deleteAll() }
    }

    private fun tryAddPlayer(player: Player): PlayerDAO? = try {
        transaction { PlayerDAO.new { nickname = player.nickname }.load() }
    } catch (ex: Exception) {
        logger.error("Error while trying to add new game: ${ex.message}")
        null
    }
}