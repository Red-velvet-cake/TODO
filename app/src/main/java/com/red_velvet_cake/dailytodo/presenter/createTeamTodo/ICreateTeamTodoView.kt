package com.red_velvet_cake.dailytodo.presenter.createPersonalTodo

import okio.IOException

interface ICreateTeamTodoView {

    fun onCreateTeamTodoSuccess(isSuccess: Boolean)
    fun onCreateTeamTodoFailure(e: IOException)
}