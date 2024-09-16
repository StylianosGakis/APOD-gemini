package com.stylianosgakis.mars.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ApodDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(apodEntities: List<ApodEntity>)

//    @Query("SELECT * FROM apod_entity WHERE title = :title")
    fun getApod(title: String): Flow<ApodEntity?>
}

