package com.red_velvet_cake.dailytodo.ui.team_todo

import com.red_velvet_cake.dailytodo.data.model.TeamTodo

interface TeamTodoView {
    fun showTodoList(todoList: List<TeamTodo>)
    fun showLoadTodosFailed()
    fun showTodoUpdateFailMessage(errorMessage: String)
    fun showEmptyTodoListState()
    fun navigateToTodoDetails(todo: TeamTodo)
    fun navigateBack()
}