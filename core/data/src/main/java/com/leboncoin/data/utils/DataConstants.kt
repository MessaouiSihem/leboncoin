package com.leboncoin.data.utils

class DataConstants {

    companion object {

        // Server
        const val BASE_URL = "https://static.leboncoin.fr/img/shared/"
        const val CONNECT_TIMEOUT_SECONDS = 30L
        const val READ_TIMEOUT_SECONDS = 30L
        const val CACHE_SIZE = (5 * 1024 * 1024).toLong() // 5MB
    }

}