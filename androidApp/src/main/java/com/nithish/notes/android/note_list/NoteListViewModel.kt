package com.nithish.notes.android.note_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nithish.notes.data.note.SearchNotes
import com.nithish.notes.domain.note.Note
import com.nithish.notes.domain.note.NoteDataSource
import com.nithish.notes.domain.time.DateTimeUtil
import com.nithish.notes.presentation.RedOrangeHex
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteDataSource: NoteDataSource,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val searchNotes = SearchNotes()

    private val notes = savedStateHandle.getStateFlow("notes", emptyList<Note>())
    private val searchText = savedStateHandle.getStateFlow("searchText","")
    private val isSearchActive = savedStateHandle.getStateFlow("isSearchActive", false)

    val state = combine(notes, searchText, isSearchActive){ notes, searchText, isSearchActive->
        NoteListState(
            notes = searchNotes.execute(notes, searchText),
            searchText,
            isSearchActive
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteListState())

    init {
        viewModelScope.launch {
            for (i in 1..10) {
                noteDataSource.insertNote(
                    Note(
                        id = null,
                        title = "Note$i",
                        content = "Content$i",
                        colorHex = RedOrangeHex,
                        created = DateTimeUtil.now(),
                    )
                )
            }
        }
    }

    // On any of the below function change - it will be updating the above and it will be saved in a state
    // which can be later used in the compose UI
    fun loadNotes(){
        viewModelScope.launch {
            savedStateHandle["notes"] = noteDataSource.getAllNotes()
        }
    }

    fun onToggleSearch(){
        savedStateHandle["isSearchActive"] = !isSearchActive.value
        if(!isSearchActive.value){
            savedStateHandle["searchText"] = ""
        }
    }

    fun onSearchTextChange(newText: String) {
        savedStateHandle["searchText"] = newText
    }

    fun deleteNoteById(id: Long){
        viewModelScope.launch {
            noteDataSource.deleteNoteById(id)
            loadNotes()
        }
    }
}