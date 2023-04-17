package com.red_velvet_cake.dailytodo.presenter.network_manager

import com.red_velvet_cake.dailytodo.data.remote.NetworkManager
import java.io.IOException

class NetworkManagerPresenter(
    private val view: NetworkManagerView,
) {
    private val networkManager = NetworkManager

    fun checkInternetConnectionStatus() {
        networkManager.hasInternetConnection(
            ::onInternetConnectionSuccess,
            ::onInternetConnectionFailure
        )
    }

    private fun onInternetConnectionSuccess(isSuccess: Boolean) {
        view.onInternetConnectionSuccess(isSuccess)
    }

    private fun onInternetConnectionFailure(e: IOException) {
        view.onInternetConnectionFailure(e)
    }
}