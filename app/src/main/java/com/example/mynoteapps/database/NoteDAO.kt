package com.example.mynoteapps.database

import androidx.lifecycle.LiveData
import androidx.room.*

// annotation @Dao. Jadi Anda bisa memakainya untuk menggunakanya ke kelas-kelas lain.
@Dao
interface NoteDAO {
    // OnConflictStrategy.IGNORE digunakan jika data yang dimasukkan sama, maka dibiarkan saja.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("SELECT * FROM note ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Note>>
}