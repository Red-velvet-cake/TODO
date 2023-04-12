package com.red_velvet_cake.dailytodo.presenter.updateTeam

import com.red_velvet_cake.dailytodo.model.UpdateTeamTodoStatusResponse
import java.io.IOException

interface IMainView {
    fun onUpdateTeamTodoStatusSuccess(
        updateTeamStatusResponse: UpdateTeamTodoStatusResponse
    )

    fun onUpdateTeamTodoStatusFailure(e: IOException)
}