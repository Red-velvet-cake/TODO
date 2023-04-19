package com.red_velvet_cake.dailytodo.ui.activity


import android.content.Intent
import android.view.LayoutInflater
import android.widget.Toast
import com.red_velvet_cake.dailytodo.data.local.SharedPrefs
import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl
import com.red_velvet_cake.dailytodo.databinding.ActivityDashboardBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseActivity
import com.red_velvet_cake.dailytodo.utils.ConnectionStatus

class DashboardActivity : BaseActivity<ActivityDashboardBinding>() {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater) -> ActivityDashboardBinding
        get() = ActivityDashboardBinding::inflate

    override fun setUp() {
        SharedPrefs.initPrefUtil(this)
        checkUserLoggedIn()
    }

    override fun addCallbacks() {}

    override fun isInternetAvailable(connectionStatus: ConnectionStatus) {
        when (connectionStatus) {
            ConnectionStatus.Available -> Toast.makeText(
                this,
                ConnectionStatus.Available.name,
                Toast.LENGTH_SHORT
            ).show()

            ConnectionStatus.Unavailable -> Toast.makeText(
                this,
                ConnectionStatus.Unavailable.name,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun checkUserLoggedIn() {
        TodoServiceImpl().checkUserLoggedIn(::lunchAuthActivity)
    }

    private fun lunchAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}