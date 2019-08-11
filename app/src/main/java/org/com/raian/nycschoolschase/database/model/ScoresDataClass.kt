package org.com.raian.nycschoolschase.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scores")
data class ScoresDataClass(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "dbn") var dbn: String? = null,
    @ColumnInfo(name = "readingScore") var readingScore: String? = null,
    @ColumnInfo(name = "mathScore") var mathScore: String? = null,
    @ColumnInfo(name = "writingScore") var writingScore: String? = null,
    @ColumnInfo(name = "schoolName") var schoolName: String? = null,
    @ColumnInfo(name = "numOfSatTestTakers") var numOfSatTestTakers: String? = null
)