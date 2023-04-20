package com.red_velvet_cake.dailytodo.ui.home

import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.model.PersonalTodo
import com.red_velvet_cake.dailytodo.data.model.TeamTodo
import com.red_velvet_cake.dailytodo.ui.home.adapter.HomeView
import com.red_velvet_cake.dailytodo.data.remote.todo_service.TodoServiceImpl

class HomePresenter(val view: HomeView) {
    private val todoServiceImpl = TodoServiceImpl()

    fun getAllTodos() {
        todoServiceImpl.getAllPersonalTodos(
            ::onGetAllPersonalTodosSuccess,
            ::onGetAllPersonalTodosFailure
        )

        todoServiceImpl.getAllTeamTodos(
            ::onGetAllTeamTodosSuccess,
            ::onGetAllTeamTodosFailure
        )
    }

    fun navigateToTeamTodoDetails(teamTodo: TeamTodo) {
        view.navigateToTeamTodoDetails(teamTodo)
    }

    fun navigateToPersonalTodoDetails(personalTodo: PersonalTodo) {
        view.navigateToPersonalTodoDetails(personalTodo)
    }

    fun navigateToAllTeamTodos() {
        view.navigateToAllTeamTodos()
    }

    fun navigateToAllPersonalTodos() {
        view.navigateToAllPersonalTodos()
    }

    private fun onGetAllPersonalTodosSuccess(getAllPersonalTodosResponse: GetAllPersonalTodosResponse) {
        view.showPersonalTodos(getAllPersonalTodosResponse)
    }

    private fun onGetAllPersonalTodosFailure(errorMessage: String) {
        view.showErrorOnPersonalTodoFailure(errorMessage)
    }

    private fun onGetAllTeamTodosSuccess(getAllTeamTodosResponse: GetAllTeamTodosResponse) {
        view.showTeamTodos(getAllTeamTodosResponse)
    }

    private fun onGetAllTeamTodosFailure(errorMessage: String) {
        if (errorMessage == "401") {
            view.navigateToLogin()
        }
        view.showErrorOnTeamTodoFailure(errorMessage)
    }
}