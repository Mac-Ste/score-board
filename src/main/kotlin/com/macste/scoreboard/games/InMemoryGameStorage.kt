package com.macste.scoreboard.games

import org.springframework.stereotype.Component

@Component
class InMemoryGameStorage : GameStorage {
    private val games = mutableSetOf<Game>()

    override fun addNewGame(game: Game) = if (games.add(game)) game else null

    override fun getAllGames() = games.toSet()

}