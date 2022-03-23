package com.example.mynoteapps.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.mynoteapps.database.Note
import com.example.mynoteapps.database.NoteDAO
import com.example.mynoteapps.database.NoteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/*
    NoteDao bisa langsung Anda gunakan dengan cara mendefinisikan RoomDatabase.
*/

class NoteRepository(application: Application) {
    private val mNotesDAO : NoteDAO
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = NoteRoomDatabase.getDatabase(application)
        mNotesDAO = db.noteDAO()
    }

    fun getAllNotes(): LiveData<List<Note>> = mNotesDAO.getAllNotes()

    fun insert(note: Note) {
        executorService.execute { mNotesDAO.insert(note) }
    }

    fun delete(note: Note) {
        executorService.execute { mNotesDAO.delete(note) }
    }

    fun update(note: Note) {
        executorService.execute { mNotesDAO.update(note) }
    }
}