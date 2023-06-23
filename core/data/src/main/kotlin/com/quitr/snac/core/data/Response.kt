package com.quitr.snac.core.data

sealed interface Response<out T>

object Error : Response<Nothing>

class Success<T>(val data: T) : Response<T>

inline fun <T, R> Response<T>.getOrElse(default: R, transform: (T) -> (R)): R =
    when (this) {
        Error -> default
        is Success -> transform(data)
    }
