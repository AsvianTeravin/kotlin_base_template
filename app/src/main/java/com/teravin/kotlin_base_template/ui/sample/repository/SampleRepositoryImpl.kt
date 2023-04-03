package com.teravin.kotlin_base_template.ui.sample.repository

import com.teravin.kotlin_base_template.database.sample.Sample
import com.teravin.kotlin_base_template.database.sample.SampleDao
import com.teravin.kotlin_base_template.util.Resource
import com.teravin.kotlin_base_template.util.TeravinFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SampleRepositoryImpl @Inject constructor(
    private val api: SampleApi,
    private val sampleDao: SampleDao,
    private val flow: TeravinFlow
) : SampleRepository {

    override suspend fun getSampleFromApi(): Flow<Resource<Sample>> = flow.block {
        api.getSample()
    }

    override suspend fun getSamplesFromDatabase(): Flow<Resource<List<Sample>>> = flow.block { sampleDao.getSamples() }

    override suspend fun insertSampleIntoDatabase(sample: Sample): Flow<Resource<Unit>> =
        flow.block { sampleDao.insert(sample) }

}