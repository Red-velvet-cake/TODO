package com.red_velvet_cake.dailytodo.presenter.create_personal_todo

import com.red_velvet_cake.dailytodo.data.model.CreateTodoPersonalResponse
import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl
import okio.IOException

class CreatePersonalTodoPresenter(
    private val view: CreatePersonalTodoView,
) {
    private val todoService = TodoServiceImpl()

    fun createPersonalTodo(
        title: String,
        description: String,
    ) {
        todoService.createPersonalTodo(
            title,
            description,
            ::onCreatePersonalTodoSuccess,
            ::onCreatePersonalTodoFailure
        )
    }

    private fun onCreatePersonalTodoSuccess(createTodoPersonalResponse: CreateTodoPersonalResponse) {
        view.onCreatePersonalTodoSuccess(createTodoPersonalResponse)
    }

    private fun onCreatePersonalTodoFailure(e: IOException) {
        view.onCreatePersonalTodoFailure(e)
    }
}