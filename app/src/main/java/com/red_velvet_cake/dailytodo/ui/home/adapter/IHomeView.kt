package com.red_velvet_cake.dailytodo.ui.home.adapter

import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import java.io.IOException

interface IHomeView {

    fun onGetAllPersonalTodosSuccess(getAllPersonalTodosResponse: GetAllPersonalTodosResponse)

    fun onGetAllPersonalTodosFailure(e: IOException)

    fun onGetAllTeamTodosSuccess(getAllTeamTodosResponse: GetAllTeamTodosResponse)
    fun onGetAllTeamTodosFailure(exception: IOException)
}