package com.red_velvet_cake.dailytodo.ui.home.adapter

import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.model.PersonalTodo
import com.red_velvet_cake.dailytodo.data.model.TeamTodo

interface HomeView {
    fun showPersonalTodos(getAllPersonalTodosResponse: GetAllPersonalTodosResponse)

    fun showErrorOnPersonalTodoFailure(errorMessage: String)

    fun showTeamTodos(getAllTeamTodosResponse: GetAllTeamTodosResponse)

    fun showErrorOnTeamTodoFailure(errorMessage: String)

    fun navigateToTeamTodoDetails(teamTodo: TeamTodo)

    fun navigateToPersonalTodoDetails(personalTodo: PersonalTodo)

    fun navigateToAllTeamTodos()

    fun navigateToAllPersonalTodos()
    fun navigateToLogin()
    fun showTryAgain()
}