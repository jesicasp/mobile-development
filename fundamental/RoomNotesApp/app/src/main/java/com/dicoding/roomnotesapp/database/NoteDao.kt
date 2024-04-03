package com.dicoding.roomnotesapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

//provide methods that your app can use to query, update, insert, and delete data in the database.
//provides the methods that the rest of the app uses to interact with data in the NOTE table.
@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) //OnConflictStrategy.IGNORE digunakan jika data yang dimasukkan sama (id), maka dibiarkan saja.
    fun insert(note: Note)
    @Update
    fun update(note: Note)
    @Delete
    fun delete(note: Note)
    @Query("SELECT * from note ORDER BY id ASC") //melakukan query atau menjalankan intruksi atau perintah untuk mengeksekusi sebuah aksi dengan anotasi @Query
    fun getAllNotes(): LiveData<List<Note>>
}