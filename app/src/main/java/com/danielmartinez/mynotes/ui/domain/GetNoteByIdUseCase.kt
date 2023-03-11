package com.danielmartinez.mynotes.ui.domain

import com.danielmartinez.mynotes.data.NotesRepository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(noteId: Int) = notesRepository.getById(noteId)
}