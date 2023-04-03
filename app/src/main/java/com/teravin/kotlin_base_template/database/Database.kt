package com.teravin.kotlin_base_template.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.teravin.kotlin_base_template.database.sample.Sample
import com.teravin.kotlin_base_template.database.sample.SampleDao
import com.teravin.kotlin_base_template.util.DateConverter
import com.teravin.kotlin_base_template.util.UUIDConverter

@Database(entities = [Sample::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class Database : RoomDatabase() {
    companion object{
        const val DB_NAME = "sample_db"
    }

    abstract fun sampleDao(): SampleDao
}