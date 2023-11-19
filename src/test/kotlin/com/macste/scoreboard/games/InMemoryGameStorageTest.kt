package com.macste.scoreboard.games

import io.kotest.matchers.collections.shouldBeEmpty
import org.junit.jupiter.api.Test

class InMemoryGameStorageTest : GameStorageContractTest(InMemoryGameStorage()) {

    @Test
    fun `should be empty on start`() {
        val gameStorage = InMemoryGameStorage()

        gameStorage.getAllGames().shouldBeEmpty()
    }

}