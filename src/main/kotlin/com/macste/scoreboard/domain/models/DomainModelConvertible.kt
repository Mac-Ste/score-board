package com.macste.scoreboard.domain.models

interface DomainModelConvertible<T> {
    fun asDomainModel(): T
}