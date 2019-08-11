package org.com.raian.nycschoolschase.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.com.raian.nycschoolschase.constants.GlobalConstants
import org.com.raian.nycschoolschase.database.dao.SchoolDao
import org.com.raian.nycschoolschase.database.dao.ScoresDao
import org.com.raian.nycschoolschase.database.model.SchoolDataClass
import org.com.raian.nycschoolschase.database.model.ScoresDataClass

@Database(
    entities = [SchoolDataClass::class, ScoresDataClass::class],
    exportSchema = false,
    version = GlobalConstants.dataBaseVersion
)

abstract class SchoolDataBase : RoomDatabase() {
    abstract fun schoolDao(): SchoolDao
    abstract fun scoresDao(): ScoresDao
}