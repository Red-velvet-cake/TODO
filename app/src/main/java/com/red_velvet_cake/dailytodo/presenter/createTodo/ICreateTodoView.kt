package com.red_velvet_cake.dailytodo.presenter.createTodo

import okio.IOException

interface ICreateTodoView {

    fun onCreatePersonalTodoSuccess(isSuccess: Boolean)
    fun onCreatePersonalTodoFailure(e: IOException)

    fun onCreateTeamTodoSuccess(isSuccess: Boolean)
    fun onCreateTeamTodoFailure(e: IOException)
}