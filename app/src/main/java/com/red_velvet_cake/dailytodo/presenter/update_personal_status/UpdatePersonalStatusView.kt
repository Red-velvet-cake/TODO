package com.red_velvet_cake.dailytodo.presenter.update_personal_status

import com.red_velvet_cake.dailytodo.data.model.UpdatePersonalStatusResponse
import okio.IOException

interface UpdatePersonalStatusView {
    fun onUpdatePersonalTodoStatusSuccess(updatePersonalStatusResponse: UpdatePersonalStatusResponse)
    fun onUpdatePersonalTodoStatusFailure(exception: IOException)
}