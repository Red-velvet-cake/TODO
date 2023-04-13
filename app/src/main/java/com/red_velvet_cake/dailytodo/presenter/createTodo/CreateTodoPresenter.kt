package com.red_velvet_cake.dailytodo.presenter.createTodo

import com.red_velvet_cake.dailytodo.data.TodoServiceImpl
import com.red_velvet_cake.dailytodo.model.TODO
import okio.IOException

class CreateTodoPresenter(
    private val view: ICreateTodoView,
) {
    private val todoService = TodoServiceImpl()

    fun createPersonalTodo(todo: TODO) {
        todoService.createPersonalTodo(
            todo,
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