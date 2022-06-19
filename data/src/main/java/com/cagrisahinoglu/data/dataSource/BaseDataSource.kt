package com.cagrisahinoglu.data.dataSource

import com.cagrisahinoglu.domain.util.DataState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

open class BaseDataSource {
    fun <T> getResult(
        coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
        call: suspend () -> T
    ): Flow<DataState<T>> = flow {
        try {
            val response = call()
            emit(
                DataState.Success(
                    data = response
                )
            )
        } catch (e: Exception) {
            emit(
                DataState.Error(
                    exception = e
                )
            )
        }
    }
        .onStart {
            emit(DataState.Loading)
        }
        .flowOn(coroutineDispatcher)
}