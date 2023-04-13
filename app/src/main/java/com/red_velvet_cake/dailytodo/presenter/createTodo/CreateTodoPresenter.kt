package com.red_velvet_cake.dailytodo.presenter.createTodo

import com.red_velvet_cake.dailytodo.data.TodoServiceImpl
import com.red_velvet_cake.dailytodo.data.model.PersonalTODORequest
import okio.IOException

class CreateTodoPresenter(
    private val view: ICreateTodoView,
) {
    private val todoService = TodoServiceImpl()

    fun createPersonalTodo(personalTodoRequest: PersonalTODORequest) {
        todoService.createPersonalTodo(
            personalTodoRequest,
            ::onCreatePersonalTodoSuccess,
            ::onCreatePersonalTodoFailure
        )
    }


    private fun onCreatePersonalTodoSuccess(isSuccess: Boolean) {
        view.onCreatePersonalTodoSuccess(isSuccess)
    }

    private fun onCreatePersonalTodoFailure(e: IOException) {
        view.onCreatePersonalTodoFailure(e)
    }
}