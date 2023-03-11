package com.danielmartinez.mynotes.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.danielmartinez.mynotes.NotesApplication
import com.danielmartinez.mynotes.data.NotesRepository
import com.danielmartinez.mynotes.data.NotesRoomDataSource
import com.danielmartinez.mynotes.databinding.ActivityDetailBinding
import com.danielmartinez.mynotes.ui.domain.GetNoteByIdUseCase
import com.danielmartinez.mynotes.ui.domain.SaveNoteUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val vm : DetailViewModel by viewModels()

    companion object {
        const val  EXTRA_NOTE_ID = "note_id"

        fun navigate(activity: AppCompatActivity, noteId: Int = -1) {
            val intent = Intent(activity, DetailActivity::class.java).apply {
                putExtra(EXTRA_NOTE_ID, noteId)
            }
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater).apply {
            setContentView(root)

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    vm.state.collect { note ->
                        editTextTitle.setText(note.title)
                        editTextDescription.setText(note.description)
                    }
                }
            }

            buttonSave.setOnClickListener {
                val title = editTextTitle.text.toString()
                val description = editTextDescription.text.toString()
                vm.save(title, description)
                finish()
            }
        }
    }
}