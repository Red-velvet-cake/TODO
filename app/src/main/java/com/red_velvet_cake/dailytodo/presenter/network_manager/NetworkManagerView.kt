package com.red_velvet_cake.dailytodo.presenter.network_manager

import java.io.IOException

interface NetworkManagerView {
    fun onInternetConnectionSuccess(isSuccess: Boolean)
    fun onInternetConnectionFailure(e: IOException)
}