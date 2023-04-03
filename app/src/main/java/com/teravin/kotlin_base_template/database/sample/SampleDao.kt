package com.teravin.kotlin_base_template.database.sample

import androidx.room.*
import java.util.UUID

@Dao
interface SampleDao {

    @Query("SELECT * from ${SampleContract.TABLE_NAME}")
    suspend fun getSamples(): List<Sample>

    @Query("SELECT * from ${SampleContract.TABLE_NAME} where ${SampleContract.SAMPLE_ID} = :id")
    suspend fun getSampleById(id: UUID): Sample

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(sample: Sample)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(sample: Sample)

    @Delete
    suspend fun deleteSample(sample: Sample)

    @Query("DELETE from ${SampleContract.TABLE_NAME}")
    suspend fun truncate()
}