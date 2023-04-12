package com.red_velvet_cake.dailytodo.presenter

import com.red_velvet_cake.dailytodo.data.model.AllTeamTodos
import okio.IOException

interface IMainView {
    fun showTeamToDoDesc(allTeamTodos: AllTeamTodos)
    fun showException(exception:IOException)
}