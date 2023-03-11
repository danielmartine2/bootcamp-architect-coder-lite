package com.danielmartinez.mynotes.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.danielmartinez.mynotes.ui.detail.DetailActivity
import com.danielmartinez.mynotes.NotesAdapter
import com.danielmartinez.mynotes.NotesApplication
import com.danielmartinez.mynotes.data.NotesRepository
import com.danielmartinez.mynotes.data.NotesRoomDataSource
import com.danielmartinez.mynotes.databinding.ActivityMainBinding
import com.danielmartinez.mynotes.ui.domain.DeleteNoteUseCase
import com.danielmartinez.mynotes.ui.domain.GetCurrentNotesUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var notesAdapter: NotesAdapter

    private val vm by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            notesAdapter = NotesAdapter(
                onNoteClick = { DetailActivity.navigate(this@MainActivity, it.id) },
                onNoteDelete = {
                    vm.onNoteDelete(it)
                }
            )
            recyclerView.adapter = notesAdapter
            fab.setOnClickListener {
                DetailActivity.navigate(this@MainActivity)
            }

            lifecycleScope.launch{
                repeatOnLifecycle(Lifecycle.State.STARTED){
                    vm.state.collect{ notes ->
                        notesAdapter.submitList(notes)
                    }
                }
            }
        }
    }
}