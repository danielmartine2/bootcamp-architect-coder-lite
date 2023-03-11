package com.danielmartinez.mynotes.ui.detail

import androidx.lifecycle.SavedStateHandle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DetailViewModelModule {

    @Provides
    fun provideNoteId(savedStateHandle: SavedStateHandle) =
        requireNotNull(savedStateHandle.get<Int>(DetailActivity.EXTRA_NOTE_ID))
}