package com.teravin.kotlin_base_template.ui.sample.repository

import com.teravin.kotlin_base_template.database.sample.Sample
import com.teravin.kotlin_base_template.util.Resource
import kotlinx.coroutines.flow.Flow

interface SampleRepository {
    suspend fun getSampleFromApi(): Flow<Resource<Sample>>

    suspend fun getSamplesFromDatabase():Flow<Resource<List<Sample>>>

    suspend fun insertSampleIntoDatabase(sample:Sample):Flow<Resource<Unit>>
}