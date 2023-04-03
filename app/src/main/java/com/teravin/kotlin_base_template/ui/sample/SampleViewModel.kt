package com.teravin.kotlin_base_template.ui.sample

import androidx.lifecycle.viewModelScope
import com.teravin.kotlin_base_template.ui.sample.repository.SampleRepositoryImpl
import com.teravin.kotlin_base_template.util.Resource
import com.teravin.kotlin_base_template.util.TeravinViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(private val repo: SampleRepositoryImpl) : TeravinViewModel() {
    fun getSample() {
        viewModelScope.launch {
            repo.getSample().collect {
                when (it) {
                    is Resource.Error -> onError(it.exception)
                    Resource.Loading -> TODO()
                    is Resource.Success -> TODO()
                }
            }
        }
    }
}