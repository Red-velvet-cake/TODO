package com.red_velvet_cake.dailytodo.presenter

import com.red_velvet_cake.dailytodo.data.model.allTeamTodos
import okio.IOException

interface IMainView {
    fun showTeamToDoDesc(allTeamTodos: allTeamTodos)
    fun showException(exception:IOException)
import com.red_velvet_cake.dailytodo.model.UpdatePersonalStatusResponse
import java.io.IOException

interface IMainView {

    fun onUpdatePersonalTodoStatusSuccess(
        updatePersonalStatusResponse: UpdatePersonalStatusResponse
    )

    fun onUpdatePersonalTodoStatusFailure(
        e: IOException
    )

}