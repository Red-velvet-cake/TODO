package com.red_velvet_cake.dailytodo.presenter

import com.red_velvet_cake.dailytodo.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.model.UpdatePersonalStatusResponse
import okio.IOException

interface IMainView {
    fun onGetAllTeamTodosSuccess(getAllTeamTodosResponse: GetAllTeamTodosResponse)
    fun onGetAllTeamTodosFailure(exception: IOException)
    fun onUpdatePersonalTodoStatusSuccess(updatePersonalStatusResponse: UpdatePersonalStatusResponse)
    fun onUpdatePersonalTodoStatusFailure(exception: IOException)
}