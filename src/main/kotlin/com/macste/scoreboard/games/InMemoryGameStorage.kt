package com.macste.scoreboard.games

import com.macste.scoreboard.domain.models.Game
import org.springframework.stereotype.Component

@Component
class InMemoryGameStorage : GameStorage {
    private val games = mutableSetOf<Game>()

    override fun addGames(games: Set<Game>): List<Game?> {
        return games.map { addGame(it) }
    }

    override fun addGame(game: Game) = if (games.add(game)) game else null

    override fun getAllGames() = games.toSet()

    override fun clearAll() {
        games.clear()
    }

}