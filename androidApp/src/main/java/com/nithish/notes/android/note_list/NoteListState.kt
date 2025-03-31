package com.nithish.notes.android.note_list

import com.nithish.notes.domain.note.Note

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val serachText: String = "",
    val isSerachActive: Boolean = false
)
