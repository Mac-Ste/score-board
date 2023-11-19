package com.macste.scoreboard.games

interface GameStorage {
    fun addGames(games: Set<Game>): List<Game?>

    fun addGame(game: Game): Game?

    fun getAllGames(): Set<Game>

    fun clearAll()
}