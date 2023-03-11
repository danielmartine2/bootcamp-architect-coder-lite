package com.danielmartinez.mynotes.ui.domain

import com.danielmartinez.mynotes.Note
import com.danielmartinez.mynotes.data.NotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentNotesUseCase @Inject constructor(private val notesRepository: NotesRepository) {
    operator fun invoke(): Flow<List<Note>> = notesRepository.currentNotes
}
