package com.macste.scoreboard.storage

import com.macste.scoreboard.domain.models.Game
import org.springframework.stereotype.Component

@Component
class InMemoryGameStorage : SimpleStorage<Game> {
    private val games = mutableSetOf<Game>()

    override fun add(item: Set<Game>): List<Game?> {
        return item.map { add(it) }
    }

    override fun add(item: Game) = if (games.add(item)) item else null

    override fun getAll() = games.toSet()

    override fun clearAll() {
        games.clear()
    }

}