package com.nithish.notes.data.note

import com.nithish.notes.domain.note.Note
import com.nithish.notes.domain.time.DateTimeUtil

class SearchNotes {
    fun execute(notes: List<Note>, query: String): List<Note>{
        if(query.isBlank()){
            return  notes
        }

        return notes.filter {
            it.title.trim().toLowerCase().contains(query.lowercase())
        }.sortedBy { DateTimeUtil.toEpochMillis(it.created) }
    }
}