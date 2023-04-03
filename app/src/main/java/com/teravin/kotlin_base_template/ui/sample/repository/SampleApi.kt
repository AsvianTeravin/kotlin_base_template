package com.teravin.kotlin_base_template.ui.sample.repository

import com.teravin.kotlin_base_template.contract.ApiContract
import com.teravin.kotlin_base_template.ui.sample.Sample
import retrofit2.http.GET

interface SampleApi {
    @GET(ApiContract.GET_SAMPLE)
    suspend fun getSample() : Sample
}