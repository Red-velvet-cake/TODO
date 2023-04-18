package com.red_velvet_cake.dailytodo.ui.personal_todo

import com.red_velvet_cake.dailytodo.data.model.PersonalTodo

interface PersonalTodoView {
    fun showTodoList(todoList: List<PersonalTodo>)
    fun showLoadTodosFailed()
    fun showTodoUpdateFailMessage(errorMessage: String)
    fun showEmptyTodoListState()
}