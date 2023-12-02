package com.kyawzinlinn.beerapp.utils

import okio.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorHandler {
    fun handle(e: Throwable) : String {
        return when (e) {
            is IOException -> "Network error occurred: ${e.message}"
            is SocketTimeoutException -> "Read time out: ${e.message}"
            is ConnectException -> "Connection failed: ${e.message}"
            is UnknownHostException -> "Failed to connect backend server!"
            else -> "An error occurred: ${e.message}"
        }
    }
}