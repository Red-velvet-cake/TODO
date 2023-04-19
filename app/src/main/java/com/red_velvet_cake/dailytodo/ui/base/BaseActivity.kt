package com.red_velvet_cake.dailytodo.ui.base

import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.viewbinding.ViewBinding

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
        setUp()
        setContentView(requireNotNull(_binding).root)
        addCallbacks()
    }

    abstract fun setUp()
    abstract fun addCallbacks()
    protected fun log(value: Any) {
        Log.v(LOG_TAG, value.toString())
    }
}