package com.red_velvet_cake.dailytodo.presenter

import com.red_velvet_cake.dailytodo.data.TodoServiceImpl
import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import java.io.IOException
class PersonalTodosPresenter (private val view : IPersonalTodosView) {
    private val todoServiceImpl = TodoServiceImpl()
    fun getAllPersonalTodos(){
        todoServiceImpl.getAllPersonalTodos(
            ::onGetAllPersonalTodosSuccess,
            ::onGetAllPersonalTodosFailure)
    }
    private fun onGetAllPersonalTodosSuccess(getAllPersonalTodosResponse: GetAllPersonalTodosResponse){
        view.onGetAllPersonalTodosSuccess(getAllPersonalTodosResponse)
    }
    private fun onGetAllPersonalTodosFailure(e : IOException){
        view.onGetAllPersonalTodosFailure(e)
    }
}