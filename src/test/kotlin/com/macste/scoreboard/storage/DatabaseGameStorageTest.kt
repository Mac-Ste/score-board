package com.macste.scoreboard.storage

import com.macste.scoreboard.utils.TestDatabase
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DatabaseGameStorageTest: GameStorageContractTest(
    DatabaseGameStorage()
) {

    @BeforeAll
    fun initDatabase() {
        TestDatabase
    }
}