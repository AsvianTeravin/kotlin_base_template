package com.teravin.kotlin_base_template.contract

abstract class ErrorContract {

    companion object {

        const val UNKNOWN = "LCL100"

        const val ERROR_TIMEOUT = "LCL001"

        const val NETWORK_ERROR = "LCL038"

    }

}