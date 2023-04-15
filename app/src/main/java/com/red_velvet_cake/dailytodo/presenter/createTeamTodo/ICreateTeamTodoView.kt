package com.red_velvet_cake.dailytodo.presenter.createTeamTodo

import com.red_velvet_cake.dailytodo.data.model.CreateTodoTeamResponse
import okio.IOException

interface ICreateTeamTodoView {

    fun onCreateTeamTodoSuccess(createTodoTeamResponse: CreateTodoTeamResponse)
    fun onCreateTeamTodoFailure(e: IOException)
}