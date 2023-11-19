package com.macste.scoreboard.games

interface GameStorage {
    fun addNewGame(game: Game): Game?

    fun getAllGames(): Set<Game>

}