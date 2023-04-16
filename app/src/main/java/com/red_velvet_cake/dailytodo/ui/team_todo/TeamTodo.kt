package com.red_velvet_cake.dailytodo.ui.team_todo

import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.model.UpdateTeamTodoStatusResponse
import okio.IOException

interface TeamTodo {
    fun onGetAllTeamTodosSuccess(getAllTeamTodosResponse: GetAllTeamTodosResponse)
    fun onGetAllTeamTodosFailure(exception: IOException)
    fun onUpdateTeamTodoStatusSuccess(updateTeamTodoStatusResponse: UpdateTeamTodoStatusResponse)
    fun onUpdateTeamTodoStatusFailure(exception: IOException)
}