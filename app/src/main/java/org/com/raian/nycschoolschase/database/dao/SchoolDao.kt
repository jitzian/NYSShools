package org.com.raian.nycschoolschase.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import org.com.raian.nycschoolschase.database.model.SchoolDataClass
import org.com.raian.nycschoolschase.database.model.UIDataClass

@Dao
interface SchoolDao {

    @Insert(onConflict = REPLACE)
    fun insert(schoolDataClass: SchoolDataClass)

    @Query("DELETE FROM school")
    fun deleteAll()

    @Query("SELECT * FROM school ORDER BY id ASC")
    fun getAll(): List<SchoolDataClass>

    @Query("SELECT *FROM school WHERE id = (:id)")
    fun getById(id: Int): SchoolDataClass

    @Query("SELECT * FROM school INNER JOIN scores on scores.dbn LIKE school.dbn")
    fun getSchoolsJoin(): List<UIDataClass>?

}