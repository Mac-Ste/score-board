package com.macste.scoreboard.games

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldBeNull
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

abstract class GameStorageContractTest(
    private val gameStorage: GameStorage
) {

    @BeforeEach
    fun setUp() {
        gameStorage.clearAll()
    }

    @AfterEach
    fun cleanUp() {
        gameStorage.clearAll()
    }

    @Test
    fun `should clear all data correctly`() {
        val game1 = Game("test-1")
        val game2 = Game("test-2")
        val game3 = Game("test-3")

        gameStorage.addGames(setOf(game1, game2, game3))
        gameStorage.getAllGames() shouldContainExactlyInAnyOrder setOf(game1, game2, game3)

        gameStorage.clearAll()
        gameStorage.getAllGames().shouldBeEmpty()
    }

    @Test
    fun `should add new game successfully`() {
        val game = Game("test")

        val result = gameStorage.addGame(game)

        result!! shouldBeEqual game
        (gameStorage.getAllGames()) shouldContainExactlyInAnyOrder setOf(game)
    }

    @Test
    fun `should return null if game already exists`() {
        val game = Game("test")

        val result = gameStorage.addGame(game)
        (gameStorage.getAllGames()) shouldContainExactlyInAnyOrder setOf(game)
        result!! shouldBeEqual game

        val result2 = gameStorage.addGame(game)
        result2.shouldBeNull()
        (gameStorage.getAllGames()) shouldContainExactlyInAnyOrder setOf(game)
    }

    @Test
    fun `should add new games successfully`() {
        val games = setOf(Game("test-1"), Game("test-2"))

        val result = gameStorage.addGames(games)

        result shouldContainExactlyInAnyOrder games
        (gameStorage.getAllGames()) shouldContainExactlyInAnyOrder games
    }

    @Test
    fun `should return nulls for games that already exist`() {
        val game1 = Game("test-1")
        val game2 = Game("test-2")
        val game3 = Game("test-3")

        val result = gameStorage.addGames(setOf(game1, game2))
        (gameStorage.getAllGames()) shouldContainExactlyInAnyOrder setOf(game1, game2)
        result shouldContainExactlyInAnyOrder setOf(game1, game2)

        val result2 = gameStorage.addGames(setOf(game2, game3))
        result2 shouldContainExactlyInAnyOrder listOf(null, game3)
        gameStorage.getAllGames() shouldContainExactlyInAnyOrder setOf(game1, game2, game3)
    }
}