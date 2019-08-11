package org.com.raian.nycschoolschase.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import org.com.raian.nycschoolschase.database.model.ScoresDataClass

@Dao
interface ScoresDao {

    @Insert(onConflict = REPLACE)
    fun insert(scoresDataClass: ScoresDataClass)

    @Query("DELETE FROM scores")
    fun deleteAll()

    @Query("SELECT * FROM scores ORDER BY id ASC")
    fun getAll(): List<ScoresDataClass>

    @Query("SELECT *FROM scores WHERE id = (:id)")
    fun getById(id: Int): ScoresDataClass

}