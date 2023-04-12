package com.red_velvet_cake.dailytodo.presenter

import com.red_velvet_cake.dailytodo.data.TodoServiceImpl

class GetAllPersonalTodosPresenter (private val view : IGetAllPersonalTodosView) {
    private val todoServiceImpl = TodoServiceImpl()

    fun getAllPersonalTodos(){
        todoServiceImpl.getAllPersonalTodos({
            view.onGetAllPersonalTodosSuccess(it)
        } , {
            view.onGetAllPersonalTodosFailure(it)
        })
    }
}