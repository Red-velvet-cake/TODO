package com.red_velvet_cake.dailytodo.presenter.create_personal_todo

import com.red_velvet_cake.dailytodo.data.model.CreateTodoPersonalResponse
import okio.IOException

interface CreatePersonalTodoView {
    fun onCreatePersonalTodoSuccess(createTodoPersonalResponse: CreateTodoPersonalResponse)
    fun onCreatePersonalTodoFailure(e: IOException)
}