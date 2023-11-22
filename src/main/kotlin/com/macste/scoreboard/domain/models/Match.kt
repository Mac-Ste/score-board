package com.macste.scoreboard.domain.models

import kotlinx.datetime.Instant

data class Match(
    val id: Int,
    val game: Game,
    val start: Instant,
    val end: Instant?,
    val status: Status
){
    enum class Status {
        IN_PROGRESS, FINISHED
    }
}