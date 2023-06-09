package com.red_velvet_cake.dailytodo.ui.home

import com.red_velvet_cake.dailytodo.data.local.LocalDataImpl
import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.model.PersonalTodo
import com.red_velvet_cake.dailytodo.data.model.TeamTodo
import com.red_velvet_cake.dailytodo.data.remote.todo_service.TodoServiceImpl
import com.red_velvet_cake.dailytodo.data.remote.util.CustomException

class HomePresenter(val view: HomeView) {
    private val todoServiceImpl = TodoServiceImpl()
    private val localDataImpl = LocalDataImpl()

    fun getAllTodos() {
        view.showLoadStatus()
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
        view.disableLoadStatus()
        view.showPersonalTodos(getAllPersonalTodosResponse)
        view.showPendingPersonalTodos(getAllPersonalTodosResponse.value.count { it.status == 0 || it.status == 1 })
        view.showCompletedPersonalTodos(getAllPersonalTodosResponse.value.count { it.status == 2 })
    }

    private fun onGetAllPersonalTodosFailure(exception: Exception) {
        when (exception) {
            is CustomException.UnauthorizedUserException -> {
                view.navigateToLogin()
            }

            else -> {
                view.showTryAgain()
                view.showErrorOnPersonalTodoFailure(exception.message.toString())
            }
        }
    }

    private fun onGetAllTeamTodosSuccess(getAllTeamTodosResponse: GetAllTeamTodosResponse) {
        view.showTeamTodos(getAllTeamTodosResponse)
        view.showPendingTeamTodos(getAllTeamTodosResponse.value.count { it.status == 0 || it.status == 1 })
        view.showCompletedTeamTodos(getAllTeamTodosResponse.value.count { it.status == 2 })
    }

    private fun onGetAllTeamTodosFailure(exception: Exception) {
        when (exception) {
            is CustomException.UnauthorizedUserException -> {
                view.navigateToLogin()
            }

            else -> {
                view.showTryAgain()
                view.showErrorOnTeamTodoFailure(exception.message.toString())
            }
        }
    }

    fun getUsername(): String {
        return localDataImpl.getUserName()
    }

    fun logout() {
        localDataImpl.clearUserToken()
        view.navigateToLogin()
    }
}