package com.red_velvet_cake.dailytodo.ui.team_todo

import com.red_velvet_cake.dailytodo.data.model.TeamTodo

interface TeamTodoView {
    fun showTodoList(todoList: List<TeamTodo>)
    fun showLoadingTodoListFailed()
    fun showToast(toastMessage: ToastMessage)
    fun showupdateTodoStatusFailed(errorMessage: String)
    fun showEmptyTodoList()
}

enum class ToastMessage {
    TODO_UPDATED_SUCCESSFULLY,
    TODO_UPDATE_FAILED
}