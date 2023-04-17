package com.red_velvet_cake.dailytodo.ui.activity


import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import com.orhanobut.hawk.Hawk
import com.red_velvet_cake.dailytodo.data.model.LoginResponse
import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl
import com.red_velvet_cake.dailytodo.databinding.ActivityDashboardBinding
import com.red_velvet_cake.dailytodo.ui.login.LoginView
import com.red_velvet_cake.dailytodo.ui.base.BaseActivity
import com.red_velvet_cake.dailytodo.utils.ConnectionStatus
import okio.IOException

class DashboardActivity : BaseActivity<ActivityDashboardBinding>(), LoginView {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater) -> ActivityDashboardBinding
        get() = ActivityDashboardBinding::inflate

    override fun setUp() {
//        lunchAuthActivity()
        Hawk.init(this).build()
        TodoServiceImpl().loginUser("test user2", "123456789", {}, {})
    }

    override fun addCallbacks() {
    }

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

    private fun lunchAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {
        Log.d("alhams", "onLoginSuccess: $loginResponse")
    }

    override fun onLoginFailure(exception: IOException) {
        Log.d("alhams", "onLoginSuccess: ${exception.message}")
    }

}