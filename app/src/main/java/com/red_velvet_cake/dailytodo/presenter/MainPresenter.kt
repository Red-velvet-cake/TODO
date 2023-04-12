package com.red_velvet_cake.dailytodo.presenter

import com.red_velvet_cake.dailytodo.data.TodoServiceImpl
import com.red_velvet_cake.dailytodo.ui.IMainPresenter

class MainPresenter {
    val todoServiceImpl=TodoServiceImpl()
    lateinit var iMainInterface:IMainPresenter
    fun presentTeamTodo()
    {
        todoServiceImpl.getTeamToDo {teamTodos ->
        iMainInterface.showTeamToDoDesc(teamTodos)
        }
    }
}