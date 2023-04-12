package com.red_velvet_cake.dailytodo.ui.activity


import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import com.red_velvet_cake.dailytodo.data.TodoServiceImpl
import com.red_velvet_cake.dailytodo.data.model.AllTeamTodos
import com.red_velvet_cake.dailytodo.databinding.ActivityDashboardBinding
import com.red_velvet_cake.dailytodo.presenter.IMainView
import com.red_velvet_cake.dailytodo.presenter.MainPresenter
import com.red_velvet_cake.dailytodo.ui.base.BaseActivity
import com.red_velvet_cake.dailytodo.utils.ConnectionStatus
import okio.IOException

class DashboardActivity : BaseActivity<ActivityDashboardBinding>(),IMainView {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater) -> ActivityDashboardBinding
        get() = ActivityDashboardBinding::inflate

    override fun setUp() {
        val presenter = MainPresenter()
        presenter.iMainView = this
        presenter.responseTeamTodo()

    }

    override fun addCallbacks() {}

    override fun isInternetAvailable(connectionStatus: ConnectionStatus) {
        when (connectionStatus) {
            ConnectionStatus.Available -> Toast.makeText(this, ConnectionStatus.Available.name, Toast.LENGTH_SHORT)
                .show()
            ConnectionStatus.Unavailable -> Toast.makeText(this, ConnectionStatus.Unavailable.name, Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun showTeamToDoDesc(allTeamTodos: AllTeamTodos) {
        Log.i("iii",allTeamTodos.value[0].description)
    }

    override fun showException(exception: IOException) {
        Log.i("iii",exception.message.toString())

    }

}