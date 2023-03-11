package com.danielmartinez.mynotes.data

import com.danielmartinez.mynotes.Note
import com.danielmartinez.mynotes.NoteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface NotesLocalDataSource {
    val currentNote: Flow<List<Note>>

    suspend fun delete(note: Note)
    suspend fun getById(noteId: Int): Note?
    suspend fun save(note: Note)
}

class NotesRoomDataSource @Inject constructor(private val noteDao: NoteDao) : NotesLocalDataSource {
    override val currentNote: Flow<List<Note>> = noteDao.getAll()

    override suspend fun delete(note: Note){
        noteDao.delete(note)
    }

    override suspend fun getById(noteId: Int): Note? = noteDao.getById(noteId)

    override suspend fun save(note: Note){
        noteDao.insert(note)
    }
}