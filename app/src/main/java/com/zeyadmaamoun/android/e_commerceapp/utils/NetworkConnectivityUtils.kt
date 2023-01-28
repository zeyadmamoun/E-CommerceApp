package com.zeyadmaamoun.android.e_commerceapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

//here is the implementation for the ConnectivityObserver interface
//we implement observer function as flow that send a Connection status as soon we got the answer from the callback.
class NetworkConnectivityUtils(context: Context) : ConnectivityObserver {
    private val connectivityManager = context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager

    override suspend fun observe(): Flow<ConnectivityObserver.ConnectionStatus> {
        return callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback(){

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch {
                        send(ConnectivityObserver.ConnectionStatus.Connected)
                    }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch {
                        send(ConnectivityObserver.ConnectionStatus.Disconnected)
                    }
                }
            }

            connectivityManager.registerDefaultNetworkCallback(callback)
            //in this await close method we unregister the callback as soon we get out of the receiver coroutine scope
            //so we don't cause any memory leaks
            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()
    }


}

