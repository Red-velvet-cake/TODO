package com.red_velvet_cake.dailytodo.presenter.createTeamTodo

import com.red_velvet_cake.dailytodo.data.model.CreateTodoTeam
import okio.IOException

interface ICreateTeamTodoView {

    fun onCreateTeamTodoSuccess(createTodoTeam: CreateTodoTeam)
    fun onCreateTeamTodoFailure(e:IOException)
}