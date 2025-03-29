package com.nithish.notes.data.note

import com.nithish.notes.database.NoteDatabase
import com.nithish.notes.domain.note.Note
import com.nithish.notes.domain.note.NoteDataSource
import com.nithish.notes.domain.time.DateTimeUtil

class SqlDelightNoteDataSource(private val db: NoteDatabase) : NoteDataSource {

    private val queries = db.noteQueries

    override suspend fun insertNote(note: Note) {
        queries.insertNote(note.id, note.title, note.content, note.colorHex, DateTimeUtil.toEpochMillis(note.created))
    }

    override suspend fun getNoteById(id: Long): Note? {
        return queries.getNoteById(id)
            .executeAsOneOrNull()
            ?.toNote()
    }

    override suspend fun getAllNotes(): List<Note> {
        return queries
            .getAllNotes()
            .executeAsList()
            .map { it.toNote() }
    }

    override suspend fun deleteNoteById(id: Long) {
        queries.deleteNoteById(id)
    }
}