package com.red_velvet_cake.dailytodo.ui.activity


import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import com.red_velvet_cake.dailytodo.databinding.ActivityDashboardBinding
import com.red_velvet_cake.dailytodo.domain.model.UpdateTeamTodoResponse
import com.red_velvet_cake.dailytodo.presenter.updateTeam.IMainView
import com.red_velvet_cake.dailytodo.presenter.updateTeam.UpdateTeamPresenter
import com.red_velvet_cake.dailytodo.ui.base.BaseActivity
import com.red_velvet_cake.dailytodo.utils.ConnectionStatus

class DashboardActivity : BaseActivity<ActivityDashboardBinding>(), IMainView {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater) -> ActivityDashboardBinding
        get() = ActivityDashboardBinding::inflate

    override fun setUp() {
        val presenter = UpdateTeamPresenter(this)
        presenter.updateTodoTeamStatus()

    }

    override fun addCallbacks() {}

    override fun isInternetAvailable(connectionStatus: ConnectionStatus) {
        when (connectionStatus) {
            ConnectionStatus.Available -> Toast.makeText(
                this,
                ConnectionStatus.Available.name,
                Toast.LENGTH_SHORT
            )
                .show()
            ConnectionStatus.Unavailable -> Toast.makeText(
                this,
                ConnectionStatus.Unavailable.name,
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    override fun updateTodoTeam(updateTeamTodoResponse: UpdateTeamTodoResponse) {
        Log.i("main", updateTeamTodoResponse.isSuccess.toString())
    }


}