package com.teravin.kotlin_base_template.util

/**
 * Created by esekiel on 23/02/2021.
 */
abstract class NavUtil {

    companion object {


        const val REQUEST_KEY = "request_key"

        const val RESULT = "result"

        const val RESULT_BACK = 100

        const val RESULT_OK = 101

        const val RESULT_FAILED = 102

        const val ERROR_REQUEST_KEY = "error"

        const val UNAUTHORIZED_DETAIL_KEY = "unauthorized_detail"

        const val SELFIE_DETAIL_KEY = "selfie_detail"

        const val SELFIE_RESULT = "selfie_result"

        const val LOCAL_ERROR = "local_error"

    }

}