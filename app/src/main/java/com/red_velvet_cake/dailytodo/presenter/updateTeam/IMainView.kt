package com.red_velvet_cake.dailytodo.presenter.updateTeam

import com.red_velvet_cake.dailytodo.model.UpdateTeamTodoResponse
import java.io.IOException

interface IMainView {
    fun onUpdateTeamTodoStatusSuccess(
        updateTeamStatusResponse: UpdateTeamTodoResponse
    )

    fun onUpdateTeamTodoStatusFailure(e: IOException)
}