package com.red_velvet_cake.dailytodo.presenter.get_all_personal_todo

import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl
import java.io.IOException

class GetAllPersonalTodosPresenter(private val view: GetAllPersonalTodosView) {
    private val todoServiceImpl = TodoServiceImpl()
    fun getAllPersonalTodos() {
        todoServiceImpl.getAllPersonalTodos(
            ::onGetAllPersonalTodosSuccess,
            ::onGetAllPersonalTodosFailure
        )
    }

    private fun onGetAllPersonalTodosSuccess(getAllPersonalTodosResponse: GetAllPersonalTodosResponse) {
        view.onGetAllPersonalTodosSuccess(getAllPersonalTodosResponse)
    }

    private fun onGetAllPersonalTodosFailure(exception: IOException) {
        view.onGetAllPersonalTodosFailure(exception)
    }
}