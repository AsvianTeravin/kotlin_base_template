package com.teravin.kotlin_base_template.ui.sample

import androidx.lifecycle.viewModelScope
import com.teravin.kotlin_base_template.database.sample.Sample
import com.teravin.kotlin_base_template.ui.sample.repository.SampleRepositoryImpl
import com.teravin.kotlin_base_template.util.Resource
import com.teravin.kotlin_base_template.util.TeravinViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(private val repo: SampleRepositoryImpl) :
    TeravinViewModel() {
    private fun getSampleFromApi() {
        viewModelScope.launch {
            coroutineContext
            repo.getSampleFromApi().collect {
                when (it) {
                    is Resource.Error -> {
                        onError(it.exception)
                    }
                    Resource.Loading -> {
                        Timber.d("Loading")
                    }
                    is Resource.Success -> {
                        Timber.d("Success")
                    }
                }

            }
        }
    }

    fun getSampleFromDatabase() {
        viewModelScope.launch {
            repo.insertSampleIntoDatabase(
                Sample(
                    sample = UUID.randomUUID().toString()
                )
            ).collect {
                when (it) {
                    is Resource.Error -> {
                        onError(it.exception)
                    }
                    Resource.Loading -> {
                        Timber.d("Loading")
                    }
                    is Resource.Success -> {
                        Timber.d("Success")
                    }
                }
            }
            repo.getSamplesFromDatabase().collect {
                when (it) {
                    is Resource.Error -> {
                        onError(it.exception)
                    }
                    Resource.Loading -> {
                        Timber.d("Loading")
                    }
                    is Resource.Success -> {
                        Timber.d("Success")
                    }
                }
            }
        }
    }

    fun onButtonClicked() {
        getSampleFromApi()
    }
}