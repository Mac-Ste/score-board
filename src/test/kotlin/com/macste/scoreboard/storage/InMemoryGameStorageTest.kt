package com.macste.scoreboard.storage

import com.macste.scoreboard.domain.models.Game
import com.macste.scoreboard.utils.randomItemsSequence
import io.kotest.matchers.collections.shouldBeEmpty
import org.junit.jupiter.api.Test

class InMemoryGameStorageTest : SimpleStorageContractTest<Game>(
    InMemoryGameStorage(),
    randomItemsSequence<Game>()
) {

    @Test
    fun `should be empty on start`() {
        val gameStorage = InMemoryGameStorage()

        gameStorage.getAll().shouldBeEmpty()
    }

}