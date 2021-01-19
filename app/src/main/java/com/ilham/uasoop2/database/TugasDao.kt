package com.ilham.uasoop2.database

import androidx.room.*
import com.ilham.uasoop2.model.Tugas

@Dao
interface TugasDao {

    @Insert
    fun insert(contact: Tugas)

    @Update
    fun update(contact: Tugas)

    @Delete
    fun delete(contact: Tugas)

    @Query("SELECT * FROM Tugas")
    fun getAll() : List<Tugas>

    @Query("SELECT * FROM tugas WHERE id = :id")
    fun getById(id: Int) : List<Tugas>
}