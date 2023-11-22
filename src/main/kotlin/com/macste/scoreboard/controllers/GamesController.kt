package com.macste.scoreboard.controllers

import com.macste.scoreboard.domain.models.Game
import com.macste.scoreboard.storage.SimpleStorage
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class GamesController(
    @Qualifier("databaseGameStorage")
    private val gameStorage: SimpleStorage<Game>
) {

    @GetMapping("/games")
    fun getGames() = gameStorage.getAll()


    @PostMapping("/games")
    fun createGame(@RequestBody game: Game): ResponseEntity<Unit> {
        val result = gameStorage.add(game)
        return if (result != null) ResponseEntity.ok().build() else ResponseEntity.badRequest().build()
    }

    @PostMapping("/bulkGames")
    fun createGames(@RequestBody games: Set<Game>): List<Game?> {
        return gameStorage.add(games)
    }
}