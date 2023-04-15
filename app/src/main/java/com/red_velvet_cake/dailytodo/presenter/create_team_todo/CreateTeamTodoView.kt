package com.red_velvet_cake.dailytodo.presenter.create_team_todo

import com.red_velvet_cake.dailytodo.data.model.CreateTodoTeamResponse
import okio.IOException

interface CreateTeamTodoView {

    fun onCreateTeamTodoSuccess(createTodoTeamResponse: CreateTodoTeamResponse)
    fun onCreateTeamTodoFailure(e: IOException)
}