package com.danielmartinez.mynotes.ui.domain

import com.danielmartinez.mynotes.Note
import com.danielmartinez.mynotes.data.NotesRepository
import javax.inject.Inject

class SaveNoteUseCase @Inject constructor(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(note: Note) {
        notesRepository.save(note)
    }
}