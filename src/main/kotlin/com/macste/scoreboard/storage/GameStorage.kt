package com.macste.scoreboard.storage

import com.macste.scoreboard.domain.models.Game

interface GameStorage {
    fun addGames(games: Set<Game>): List<Game?>

    fun addGame(game: Game): Game?

    fun getAllGames(): Set<Game>

    fun clearAll()
}