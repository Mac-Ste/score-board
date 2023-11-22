package com.macste.scoreboard.storage

import com.macste.scoreboard.domain.models.Player
import com.macste.scoreboard.utils.TestDatabase
import com.macste.scoreboard.utils.randomItemsSequence
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DatabasePlayerStorageTest : SimpleStorageContractTest<Player>(
    DatabasePlayerStorage(),
    randomItemsSequence()
) {
    @BeforeAll
    fun initDatabase() {
        TestDatabase
    }
}