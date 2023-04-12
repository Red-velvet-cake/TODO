package com.red_velvet_cake.dailytodo.presenter

import com.red_velvet_cake.dailytodo.data.TodoServiceImpl
import com.red_velvet_cake.dailytodo.model.UpdatePersonalStatusResponse
import java.io.IOException


class MainPresenter(private val view: IMainView) {

    private val todoServiceImpl = TodoServiceImpl()

    fun updatePersonalTodoStatus(
        userId: String, newTodoStatus: Int
    ) {
        todoServiceImpl.updatePersonalTodoStatus(
            userId,
            newTodoStatus,
            ::onUpdatePersonalTodoStatusSuccess,
            ::onUpdatePersonalTodoStatusFailure
        )
    }

    private fun onUpdatePersonalTodoStatusSuccess(updatePersonalStatusResponse: UpdatePersonalStatusResponse) {
        view.onUpdatePersonalTodoStatusSuccess(updatePersonalStatusResponse)
    }

    private fun onUpdatePersonalTodoStatusFailure(exception: IOException) {
        view.onUpdatePersonalTodoStatusFailure(exception)
    }
}

