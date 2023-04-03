package com.teravin.kotlin_base_template.ui.error

import com.teravin.kotlin_base_template.util.LocalError
import com.teravin.kotlin_base_template.util.NavUtil
import com.teravin.kotlin_base_template.util.TeravinViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ErrorViewModel @Inject constructor(): TeravinViewModel() {
    private val _localError = MutableStateFlow<LocalError?>(null)
    val localError = _localError.asStateFlow()

    private val _result = MutableStateFlow(0)
    val result = _result.asStateFlow()

    fun setResultOK() {
        _result.value = NavUtil.RESULT_OK
    }

    fun setLocalError(localError: LocalError){
        _localError.value = localError
    }
}