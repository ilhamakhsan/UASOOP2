package com.ilham.uasoop2.database

import androidx.room.*
import com.ilham.uasoop2.model.Bimbingan

@Dao
interface BimbinganDao {

    @Insert
    fun insert(contact: Bimbingan)

    @Update
    fun update(contact: Bimbingan)

    @Delete
    fun delete(contact: Bimbingan)

    @Query("SELECT * FROM Bimbingan")
    fun getAll() : List<Bimbingan>

    @Query("SELECT * FROM Bimbingan WHERE id = :id")
    fun getById(id: Int) : List<Bimbingan>
}