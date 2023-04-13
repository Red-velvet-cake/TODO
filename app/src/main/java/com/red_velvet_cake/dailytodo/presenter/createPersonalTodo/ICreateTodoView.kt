package com.red_velvet_cake.dailytodo.presenter.createPersonalTodo

import okio.IOException

interface ICreatePersonalTodoView {

    fun onCreatePersonalTodoSuccess(isSuccess: Boolean)
    fun onCreatePersonalTodoFailure(e: IOException)
}