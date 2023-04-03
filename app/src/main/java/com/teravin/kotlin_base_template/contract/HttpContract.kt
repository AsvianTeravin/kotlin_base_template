package com.teravin.kotlin_base_template.contract


abstract class HttpContract {

    companion object {
        const val DEFAULT_READ_TIMEOUT_IN_SECOND: Long = 45
        const val DEFAULT_WRITE_TIMEOUT_IN_SECOND: Long = 45
        const val DEFAULT_CONNECT_TIMEOUT_IN_SECOND: Long = 45
    }
}