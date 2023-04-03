package com.teravin.kotlin_base_template.ui.sample.repository

import com.teravin.kotlin_base_template.util.TeravinFlow
import javax.inject.Inject

class SampleRepositoryImpl @Inject constructor(private val api: SampleApi, private val flow: TeravinFlow) : SampleRepository {

    override suspend fun getSample() = flow.block {
        val response = api.getSample()
        response
    }

}