package com.danielmartinez.mynotes.ui.main

import androidx.lifecycle.*
import com.danielmartinez.mynotes.Note
import com.danielmartinez.mynotes.NotesDatabase
import com.danielmartinez.mynotes.data.NotesRepository
import com.danielmartinez.mynotes.ui.domain.DeleteNoteUseCase
import com.danielmartinez.mynotes.ui.domain.GetCurrentNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrentNotesUseCase: GetCurrentNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    val state = getCurrentNotesUseCase()

    fun onNoteDelete(note: Note){
        viewModelScope.launch {
            deleteNoteUseCase(note)
        }
    }
}

/*
@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val getCurrentNotesUseCase: GetCurrentNotesUseCase,
                           private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getCurrentNotesUseCase, deleteNoteUseCase) as T
    }
}
*/