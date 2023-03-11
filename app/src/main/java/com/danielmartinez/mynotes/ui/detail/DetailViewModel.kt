package com.danielmartinez.mynotes.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.danielmartinez.mynotes.Note
import com.danielmartinez.mynotes.data.NotesRepository
import com.danielmartinez.mynotes.ui.domain.GetNoteByIdUseCase
import com.danielmartinez.mynotes.ui.domain.SaveNoteUseCase
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val noteId: Int
): ViewModel() {

    private val _state = MutableStateFlow(Note(0, "", ""))
    val state: StateFlow<Note> = _state.asStateFlow()

    init {
        viewModelScope.launch{
            val note = getNoteByIdUseCase(noteId)
            if (note != null) {
                _state.value = note
            }
        }
    }

    fun save(title: String, description: String) {
        viewModelScope.launch {
            val note = _state.value.copy(title = title, description = description)
            saveNoteUseCase(note)
        }
    }
}

/*
@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val noteId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(getNoteByIdUseCase, saveNoteUseCase, noteId) as T
    }
}
 */