package com.red_velvet_cake.dailytodo.presenter

import com.red_velvet_cake.dailytodo.model.GetAllPersonalTodosResponse
import java.io.IOException


interface IPersonalTodosView {
    fun onGetAllPersonalTodosSuccess(
        getAllPersonalTodosResponse: GetAllPersonalTodosResponse
    )
    fun onGetAllPersonalTodosFailure (
        e: IOException
    )
}