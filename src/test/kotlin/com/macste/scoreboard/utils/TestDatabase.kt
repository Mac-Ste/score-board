package com.macste.scoreboard.utils

import com.macste.scoreboard.db.exposed.models.GameTable
import com.macste.scoreboard.db.exposed.models.PlayerTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.testcontainers.containers.PostgreSQLContainer

object TestDatabase {

    private val postgresContainer = PostgreSQLContainer<Nothing>("postgres:16.1").apply {
        withDatabaseName("testdb")
        withUsername("test")
        withPassword("test")
        start()
    }

    init {
        val config = HikariConfig().apply {
            jdbcUrl = postgresContainer.jdbcUrl
            username = postgresContainer.username
            password = postgresContainer.password
            driverClassName = "org.postgresql.Driver"
            maximumPoolSize = 10
        }
        val dataSource = HikariDataSource(config)

        // This doesn't connect to the database but provides a descriptor for future use
        // In the main app, we would do this on system start up
        // https://github.com/JetBrains/Exposed/wiki/Database-and-DataSource
        Database.connect(dataSource)

        // Create the schema
        transaction {
            SchemaUtils.create(PlayerTable, GameTable)
        }
    }
}