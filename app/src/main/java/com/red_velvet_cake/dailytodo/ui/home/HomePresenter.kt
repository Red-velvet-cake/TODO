package com.red_velvet_cake.dailytodo.ui.home

import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl
import com.red_velvet_cake.dailytodo.ui.home.adapter.IHomeView
import java.io.IOException

class HomePresenter(val view: IHomeView) {
    private val todoServiceImpl = TodoServiceImpl()
    fun getAllTodos() {
        todoServiceImpl.getAllPersonalTodos(
            ::onGetAllPersonalTodosSuccess,
            ::onGetAllPersonalTodosFailure
        )

        todoServiceImpl.getAllTeamTodos(
            ::onGetAllTeamTodosSuccess,
            ::onGetAllTeamTodosFailure
        )
    }


    private fun onGetAllPersonalTodosSuccess(getAllPersonalTodosResponse: GetAllPersonalTodosResponse) {
        view.onGetAllPersonalTodosSuccess(getAllPersonalTodosResponse)
    }

    private fun onGetAllPersonalTodosFailure(exception: IOException) {
        view.onGetAllPersonalTodosFailure(exception)
    }

    private fun onGetAllTeamTodosSuccess(getAllTeamTodosResponse: GetAllTeamTodosResponse) {
        view.onGetAllTeamTodosSuccess(getAllTeamTodosResponse)
    }

    private fun onGetAllTeamTodosFailure(exception: IOException) {
        view.onGetAllTeamTodosFailure(exception)
    }


}