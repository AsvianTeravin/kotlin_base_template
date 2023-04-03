package com.teravin.kotlin_base_template.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class TeravinFlow @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher
) {
    fun <T> block(
        block: suspend () -> T
    ): Flow<Resource<T>> = flow {
        emit(Resource.Loading)

        emit(Resource.Success(block()))
    }
        .catch {
            emit(Resource.Error(it as Exception))
        }
        .cancellable()
        .flowOn(ioDispatcher)

}