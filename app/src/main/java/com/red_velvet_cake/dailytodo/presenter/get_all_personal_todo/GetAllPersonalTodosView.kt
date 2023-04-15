package com.red_velvet_cake.dailytodo.presenter.get_all_personal_todo

import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import java.io.IOException


interface GetAllPersonalTodosView {
    fun onGetAllPersonalTodosSuccess(
        getAllPersonalTodosResponse: GetAllPersonalTodosResponse
    )

    fun onGetAllPersonalTodosFailure(
        e: IOException
    )
}