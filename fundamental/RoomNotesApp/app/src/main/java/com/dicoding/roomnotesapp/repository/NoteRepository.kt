package com.dicoding.roomnotesapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.roomnotesapp.database.Note
import com.dicoding.roomnotesapp.database.NoteDao
import com.dicoding.roomnotesapp.database.NoteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

//Kelas ini sebagai penghubung antara ViewModel dengan database atau resource data.
class NoteRepository(application: Application) {
    private val mNotesDao: NoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = NoteRoomDatabase.getDatabase(application)
        mNotesDao = db.noteDao()
    }
    fun getAllNotes(): LiveData<List<Note>> = mNotesDao.getAllNotes()
    fun insert(note: Note) {
        executorService.execute { mNotesDao.insert(note) }
    }
    fun delete(note: Note) {
        executorService.execute { mNotesDao.delete(note) }
    }
    fun update(note: Note) {
        executorService.execute { mNotesDao.update(note) }
    }
}