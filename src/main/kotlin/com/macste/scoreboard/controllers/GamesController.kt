package com.macste.scoreboard.controllers

import com.macste.scoreboard.games.Game
import com.macste.scoreboard.games.GameStorage
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class GamesController(
    private val gameStorage: GameStorage
) {

    @GetMapping("/games")
    fun getGames() = gameStorage.getAllGames()


    @PostMapping("/games")
    fun createGame(@RequestBody game: Game): ResponseEntity<Unit> {
        val result = gameStorage.addGame(game)
        return if (result != null) ResponseEntity.ok().build() else ResponseEntity.badRequest().build()
    }

    @PostMapping("/bulkGames")
    fun createGames(@RequestBody games: Set<Game>): List<Game?> {
        return gameStorage.addGames(games)
    }
}