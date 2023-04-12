package com.red_velvet_cake.dailytodo.ui.activity


import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import com.red_velvet_cake.dailytodo.databinding.ActivityDashboardBinding
import com.red_velvet_cake.dailytodo.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.presenter.GetAllPersonalTodosPresenter
import com.red_velvet_cake.dailytodo.presenter.IGetAllPersonalTodosView
import com.red_velvet_cake.dailytodo.ui.base.BaseActivity
import com.red_velvet_cake.dailytodo.utils.ConnectionStatus
import java.io.IOException

class DashboardActivity : BaseActivity<ActivityDashboardBinding>() , IGetAllPersonalTodosView {
    override val LOG_TAG: String = this::class.java.name
    lateinit var getAllPersonalTodosPresenter: GetAllPersonalTodosPresenter
    override val bindingInflater: (LayoutInflater) -> ActivityDashboardBinding
        get() = ActivityDashboardBinding::inflate

    override fun setUp() {

        getAllPersonalTodosPresenter = GetAllPersonalTodosPresenter(this)
        getAllPersonalTodosPresenter.getAllPersonalTodos()
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

    override fun onGetAllPersonalTodosSuccess(getAllPersonalTodosResponse: GetAllPersonalTodosResponse) {
        Log.i("iii" , getAllPersonalTodosResponse.value[0].title)
    }

    override fun onGetAllPersonalTodosFailure(e: IOException) {
        Log.i("iii" , "fail ${e.message}")
    }


}