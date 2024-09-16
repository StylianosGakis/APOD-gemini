package com.stylianosgakis.mars.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import kotlinx.datetime.LocalDate

//@Database(
//    entities = [ApodEntity::class],
//    version = 1,
//)
//@TypeConverters(LocalDateConverter::class)
abstract class AppDatabase : RoomDatabase() {
//    abstract fun apodDao(): ApodDao
}

//class LocalDateConverter {
//    @TypeConverter
//    fun parse(value: String): LocalDate {
//        return LocalDate.parse(value)
//    }
//
//    @TypeConverter
//    fun toString(value: LocalDate): String {
//        return value.toString()
//    }
//}