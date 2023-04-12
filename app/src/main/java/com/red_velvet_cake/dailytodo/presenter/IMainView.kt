package com.red_velvet_cake.dailytodo.presenter

import com.red_velvet_cake.dailytodo.model.GetAllTeamTodosResponse
import okio.IOException

interface IMainView {
    fun onGetAllTeamTodosSuccess(getAllTeamTodosResponse: GetAllTeamTodosResponse)
    fun onGetAllTeamTodosFailure(exception: IOException)
}