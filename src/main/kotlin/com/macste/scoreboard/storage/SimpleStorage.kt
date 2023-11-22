package com.macste.scoreboard.storage

import com.macste.scoreboard.domain.models.Game

interface SimpleStorage<T> {
    fun add(item: Set<T>): List<T?>

    fun add(item: T): T?

    fun getAll(): Set<T>

    fun clearAll()
}