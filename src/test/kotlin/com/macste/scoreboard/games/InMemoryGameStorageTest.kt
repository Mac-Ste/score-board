package com.macste.scoreboard.games

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldBeNull
import org.junit.jupiter.api.Test

class InMemoryGameStorageTest {

    @Test
    fun `should be empty on start`() {
        val gameStorage = InMemoryGameStorage()

        gameStorage.getAllGames().shouldBeEmpty()
    }

    @Test
    fun `should add new games successfully`() {
        val gameStorage = InMemoryGameStorage()
        val game = Game("Fun Game")
        gameStorage.getAllGames().shouldBeEmpty()

        val result = gameStorage.addNewGame(game)

        result!! shouldBeEqual game
        gameStorage.getAllGames() shouldContainExactlyInAnyOrder setOf(game)
    }

    @Test
    fun `should return null if game already exist`() {
        val gameStorage = InMemoryGameStorage()
        val game = Game("Fun Game")
        gameStorage.getAllGames().shouldBeEmpty()

        val result = gameStorage.addNewGame(game)
        gameStorage.getAllGames() shouldContainExactlyInAnyOrder setOf(game)

        val result2 = gameStorage.addNewGame(game)

        result!! shouldBeEqual game
        result2.shouldBeNull()
        gameStorage.getAllGames() shouldContainExactlyInAnyOrder setOf(game)
    }

}