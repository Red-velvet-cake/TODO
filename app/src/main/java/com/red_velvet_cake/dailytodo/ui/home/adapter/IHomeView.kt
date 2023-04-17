package com.red_velvet_cake.dailytodo.ui.home.adapter

import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.model.PersonalTodo
import com.red_velvet_cake.dailytodo.data.model.TeamTodo
import java.io.IOException

interface IHomeView {

    fun showPersonalTodos(getAllPersonalTodosResponse: GetAllPersonalTodosResponse)

    fun showErrorOnPersonalTodoFailure(e: IOException)

    fun showTeamTodos(getAllTeamTodosResponse: GetAllTeamTodosResponse)
    fun showErrorOnTeamTodoFailure(exception: IOException)

    fun navigateToTeamTodoDetails(teamTodo: TeamTodo)
    fun navigateToPersonalTodoDetails(personalTodo: PersonalTodo)
}