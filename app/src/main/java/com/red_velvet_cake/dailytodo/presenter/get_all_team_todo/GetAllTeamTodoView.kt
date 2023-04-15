package com.red_velvet_cake.dailytodo.presenter.get_all_team_todo

import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import okio.IOException

interface GetAllTeamTodoView {
    fun onGetAllTeamTodosSuccess(getAllTeamTodosResponse: GetAllTeamTodosResponse)
    fun onGetAllTeamTodosFailure(exception: IOException)
}