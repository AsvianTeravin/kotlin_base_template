package com.teravin.kotlin_base_template.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class TeravinFlow @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher
) {
    fun <T> block(
        block: suspend () -> T
    ): Flow<Resource<T>> = flow {
        try {
            emit(Resource.Loading)

            emit(Resource.Success(block()))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }.flowOn(ioDispatcher)
}

