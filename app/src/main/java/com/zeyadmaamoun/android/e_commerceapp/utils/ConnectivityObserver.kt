package com.zeyadmaamoun.android.e_commerceapp.utils

import kotlinx.coroutines.flow.Flow

//this interface for tracking internet connectivity feature and the there are two status
//for connection --> (Connected,Disconnected)
interface ConnectivityObserver {
    suspend fun observe(): Flow<ConnectionStatus>
    enum class ConnectionStatus{ Connected,Disconnected }
}