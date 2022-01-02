package com.hg_studio.oneline

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OnelineDao {
    @Query("SELECT * FROM OnelineTable")
    fun getOnelines(): List<Oneline>

    @Query("SELECT * FROM OnelineTable WHERE writer = :writer")
    fun getOnelinesByWriter(writer: Int): List<Oneline>?

    @Insert
    fun writeOneline(oneline: Oneline)

    @Query("DELETE FROM OnelineTable WHERE idx = :id")
    fun removeOneline(id: Int)

    @Query("UPDATE OnelineTable SET isDelete =:isDelete WHERE idx=:id")
    fun deleteOneline(id: Int, isDelete: String)
}