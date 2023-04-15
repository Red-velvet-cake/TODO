package com.red_velvet_cake.dailytodo.presenter.update_team_status

import com.red_velvet_cake.dailytodo.data.model.UpdateTeamTodoStatusResponse
import okio.IOException

interface UpdateTeamTodoStatusView {
    fun onUpdateTeamTodoStatusSuccess(updateTeamStatusResponse: UpdateTeamTodoStatusResponse)
    fun onUpdateTeamTodoStatusFailure(exception: IOException)
}