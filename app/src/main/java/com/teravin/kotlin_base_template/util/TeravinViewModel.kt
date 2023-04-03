package com.teravin.kotlin_base_template.util

import androidx.lifecycle.ViewModel
import com.teravin.kotlin_base_template.R
import com.teravin.kotlin_base_template.contract.ErrorContract
import com.teravin.support.AppException
import com.teravin.support.UnresolvableException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException

open class TeravinViewModel : ViewModel() {
    protected val _errorState = MutableStateFlow<LocalError?>(null)
    val errorState = _errorState.asStateFlow()

    open fun onError(exception: Exception) {
        Timber.e(exception)
        val stackTrace = exception.stackTraceToString()
        when (exception) {
            is SocketTimeoutException -> {
                _errorState.value = LocalError(ErrorContract.ERROR_TIMEOUT, R.string.time_out, "", stackTrace)
            }
            is IOException -> {
                _errorState.value = LocalError(ErrorContract.NETWORK_ERROR, R.string.network_error, "", stackTrace)
            }
            is AppException -> {
                _errorState.value = LocalError(
                    exception.code, 0, exception.message!!, stackTrace
                )
            }
            is UnresolvableException -> {
                _errorState.value = LocalError(
                    exception.code, exception.messageStringId, "", stackTrace
                )
            }
            else -> {
                _errorState.value = LocalError(
                    ErrorContract.UNKNOWN, R.string.default_error, "", stackTrace
                )
            }
        }
    }
}