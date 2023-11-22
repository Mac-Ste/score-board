package com.macste.scoreboard.storage

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldBeNull
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

abstract class SimpleStorageContractTest<T : Any>(
    private val simpleStorage: SimpleStorage<T>,
    itemsSequence: Sequence<T>
) {

    private val items = itemsSequence.take(3).toList()


    @BeforeEach
    fun setUp() {
        simpleStorage.clearAll()
    }

    @AfterEach
    fun cleanUp() {
        simpleStorage.clearAll()
    }

    @Test
    fun `should clear all data correctly`() {
        simpleStorage.add(setOf(items[0], items[1], items[2]))
        simpleStorage.getAll() shouldContainExactlyInAnyOrder setOf(items[0], items[1], items[2])

        simpleStorage.clearAll()
        simpleStorage.getAll().shouldBeEmpty()
    }

    @Test
    fun `should add new game successfully`() {
        val result = simpleStorage.add(items[0])

        result!! shouldBeEqual items[0]
        (simpleStorage.getAll()) shouldContainExactlyInAnyOrder setOf(items[0])
    }

    @Test
    fun `should return null if game already exists`() {
        val result = simpleStorage.add(items[0])
        (simpleStorage.getAll()) shouldContainExactlyInAnyOrder setOf(items[0])
        result!! shouldBeEqual items[0]

        val result2 = simpleStorage.add(items[0])
        result2.shouldBeNull()
        (simpleStorage.getAll()) shouldContainExactlyInAnyOrder setOf(items[0])
    }

    @Test
    fun `should add new items successfully`() {
        val result = simpleStorage.add(items.toSet())

        result shouldContainExactlyInAnyOrder items
        (simpleStorage.getAll()) shouldContainExactlyInAnyOrder items
    }

    @Test
    fun `should return nulls for items that already exist`() {
        val result = simpleStorage.add(setOf(items[0], items[1]))
        (simpleStorage.getAll()) shouldContainExactlyInAnyOrder setOf(items[0], items[1])
        result shouldContainExactlyInAnyOrder setOf(items[0], items[1])

        val result2 = simpleStorage.add(setOf(items[1], items[2]))
        result2 shouldContainExactlyInAnyOrder listOf(null, items[2])
        simpleStorage.getAll() shouldContainExactlyInAnyOrder setOf(items[0], items[1], items[2])
    }
}