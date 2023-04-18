package com.red_velvet_cake.dailytodo.ui.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.viewbinding.ViewBinding
import com.red_velvet_cake.dailytodo.utils.ConnectionStatus

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    abstract val LOG_TAG: String
    abstract val bindingInflater: (LayoutInflater) -> VB
    private var _binding: VB? = null

    protected val binding: VB
        get() = _binding as VB


    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var networkCallback: ConnectivityManager.NetworkCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        _binding = bindingInflater(layoutInflater)
        checkConnectionStatus()
        setUp()
        setContentView(requireNotNull(_binding).root)
        addCallbacks()
    }

    abstract fun setUp()
    abstract fun addCallbacks()
    abstract fun isInternetAvailable(connectionStatus: ConnectionStatus)

    private fun checkConnectionStatus() {
        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                isInternetAvailable(ConnectionStatus.Available)
            }

            override fun onLost(network: Network) {
                isInternetAvailable(ConnectionStatus.Unavailable)
            }
        }
    }


    protected fun log(value: Any) {
        Log.v(LOG_TAG, value.toString())
    }

    override fun onResume() {
        super.onResume()
        val builder = NetworkRequest.Builder()
        connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
    }

    override fun onPause() {
        super.onPause()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}