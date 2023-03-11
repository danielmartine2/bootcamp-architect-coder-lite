package com.danielmartinez.mynotes.di

import android.app.Application
import androidx.room.Room
import com.danielmartinez.mynotes.NotesDatabase
import com.danielmartinez.mynotes.data.NotesLocalDataSource
import com.danielmartinez.mynotes.data.NotesRoomDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideNotesDatabase(app: Application) = Room.databaseBuilder(
        app,
        NotesDatabase::class.java,
        "notes.db"
    ).build()

    @Provides
    fun provideNotesDao(db: NotesDatabase) = db.notesDao()

    @Provides
    fun provideNotesLocalDataSource(notesRoomDataSource: NotesRoomDataSource): NotesLocalDataSource =
        notesRoomDataSource
}