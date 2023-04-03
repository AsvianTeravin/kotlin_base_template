package com.teravin.kotlin_base_template.ui.sample.repository

import com.teravin.kotlin_base_template.database.Database
import com.teravin.kotlin_base_template.database.sample.SampleDao
import dagger.Binds
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
    fun provideSampleDao(database: Database): SampleDao = database.sampleDao()

    @Module
    @InstallIn(ViewModelComponent::class)
    abstract class SampleBinder {
        @Binds
        abstract fun bindSampleRepository(sampleRepository: SampleRepository): SampleRepository
    }
}