package com.macste.scoreboard.controllers

import com.macste.scoreboard.db.exposed.models.GameDAO
import com.macste.scoreboard.db.exposed.models.GameTable
import com.macste.scoreboard.db.exposed.models.MatchDAO
import com.macste.scoreboard.domain.models.Game
import org.jetbrains.exposed.dao.load
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MatchesController {

    @GetMapping("/matches")
    fun getMatches() = transaction { MatchDAO.all().map { it.asDomainModel() } }.toSet()

    @PostMapping("/match")
    fun createMatch(@RequestBody game: Game) = transaction {
        GameDAO.find { GameTable.title eq game.title }.firstOrNull()
            ?.let {
                val match = MatchDAO.new { this.game = it.id }.load()
                ResponseEntity.ok(match.asDomainModel())
            } ?: ResponseEntity.badRequest()
    }
}