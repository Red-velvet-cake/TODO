package com.red_velvet_cake.dailytodo.presenter

import com.red_velvet_cake.dailytodo.data.TodoServiceImpl
import com.red_velvet_cake.dailytodo.model.GetAllTeamTodosResponse
import okio.IOException

class MainPresenter {
    private val todoServiceImpl = TodoServiceImpl()
    lateinit var iMainView: IMainView
    fun responseTeamTodo() {
        todoServiceImpl.getAllTeamTodos(
            ::onGetAllTeamTodosSuccess,
            ::onGetAllTeamTodosFailure
        )
    }

    private fun onGetAllTeamTodosSuccess(getAllTeamTodosResponse: GetAllTeamTodosResponse) {
        iMainView.onGetAllTeamTodosSuccess(getAllTeamTodosResponse)
    }

    private fun onGetAllTeamTodosFailure(exception: IOException) {
        iMainView.onGetAllTeamTodosFailure(exception)
    }
}
