package com.nithish.notes.database


internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = NoteDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.noteQueries
}