package com.teravin.kotlin_base_template.ui.sample.repository

import com.teravin.kotlin_base_template.util.TeravinFlow
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object SampleModule {
    @Provides
    fun provideSampleApi(retrofit: Retrofit): SampleApi = retrofit.create(SampleApi::class.java)

    @Provides
    fun provideSampleRepository(api: SampleApi, flow: TeravinFlow): SampleRepository = SampleRepositoryImpl(api, flow)
}