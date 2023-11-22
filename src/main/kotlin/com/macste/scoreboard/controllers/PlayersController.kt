package com.macste.scoreboard.controllers

import com.macste.scoreboard.domain.models.Player
import com.macste.scoreboard.storage.SimpleStorage
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PlayersController(
    @Qualifier("databasePlayerStorage")
    private val playerStorage: SimpleStorage<Player>
) {

    @GetMapping("/players")
    fun getGames() = playerStorage.getAll()


    @PostMapping("/players")
    fun createGame(@RequestBody player: Player): ResponseEntity<Unit> {
        val result = playerStorage.add(player)
        return if (result != null) ResponseEntity.ok().build() else ResponseEntity.badRequest().build()
    }

    @PostMapping("/bulkPlayers")
    fun createGames(@RequestBody players: Set<Player>): List<Player?> {
        return playerStorage.add(players)
    }
}