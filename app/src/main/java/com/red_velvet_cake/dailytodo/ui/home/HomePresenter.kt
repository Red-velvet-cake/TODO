package com.red_velvet_cake.dailytodo.ui.home

import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.model.PersonalTodo
import com.red_velvet_cake.dailytodo.data.model.TeamTodo
import com.red_velvet_cake.dailytodo.data.remote.todo_service.TodoServiceImpl
import com.red_velvet_cake.dailytodo.ui.home.adapter.IHomeView
import java.io.IOException

class HomePresenter(val view: IHomeView) {
    private val apiService = TodoServiceImpl()

    fun getAllTodos() {
        apiService.getAllPersonalTodos(
            ::onGetAllPersonalTodosSuccess,
            ::onGetAllPersonalTodosFailure
        )

        apiService.getAllTeamTodos(
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

    private fun onGetAllPersonalTodosFailure(exception: IOException) {
        view.showErrorOnPersonalTodoFailure(exception)
    }

    private fun onGetAllTeamTodosSuccess(getAllTeamTodosResponse: GetAllTeamTodosResponse) {
        view.showTeamTodos(getAllTeamTodosResponse)
    }

    private fun onGetAllTeamTodosFailure(exception: IOException) {
        view.showErrorOnTeamTodoFailure(exception)
    }


}