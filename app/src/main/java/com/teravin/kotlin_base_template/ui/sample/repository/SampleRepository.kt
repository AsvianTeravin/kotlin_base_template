package com.teravin.kotlin_base_template.ui.sample.repository

import com.teravin.kotlin_base_template.ui.sample.Sample
import com.teravin.kotlin_base_template.util.Resource
import kotlinx.coroutines.flow.Flow

interface SampleRepository {
    suspend fun getSample(): Flow<Resource<Sample>>
}