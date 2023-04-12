package com.red_velvet_cake.dailytodo.presenter

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