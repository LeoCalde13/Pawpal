package com.caldeira.pawpal.model

import retrofit2.Response

sealed class NetworkResult<T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error<T>(val code: Int, val message: String?) : NetworkResult<T>()
    data class Exception<T>(val e: Throwable) : NetworkResult<T>()
}

suspend fun <T : Any> handleResult(request: suspend () -> Response<T>): NetworkResult<T> {
    return try {
        val response = request()
        val body = response.body()

        if (response.isSuccessful && body != null) {
            NetworkResult.Success(data = body)
        } else {
            NetworkResult.Error(code = response.code(), message = response.message())
        }
    } catch (e: Throwable) {
        NetworkResult.Exception(e)
    }
}