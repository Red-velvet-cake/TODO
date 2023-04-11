package com.red_velvet_cake.dailytodo.presenter

import com.red_velvet_cake.dailytodo.data.TodoServiceImpl


class MainPresenter(private val view: IMainView) {

    private val todoServiceImpl = TodoServiceImpl()

    fun updatePersonalTodoStatus() {
        todoServiceImpl.updatePersonalTodoStatus({
            view.onUpdatePersonalTodoStatusSuccess(it)
        }, {
            view.onUpdatePersonalTodoStatusFailure(it)
        })
    }
}