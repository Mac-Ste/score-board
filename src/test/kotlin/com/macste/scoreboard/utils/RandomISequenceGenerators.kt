package com.macste.scoreboard.utils

import com.macste.scoreboard.domain.models.Game
import com.macste.scoreboard.domain.models.Player
import kotlin.random.Random


inline fun <reified T> randomItemsSequence(): Sequence<T> = when (T::class) {
    String::class -> generateSequence { "string-${Random.nextInt()}" as T }
    Game::class -> generateSequence { Game("game-${Random.nextInt()}") as T }
    Player::class -> generateSequence { Player("player-${Random.nextInt()}") as T }
    else -> throw UnsupportedOperationException("${T::class.java.name} is not supported for random generation")

}