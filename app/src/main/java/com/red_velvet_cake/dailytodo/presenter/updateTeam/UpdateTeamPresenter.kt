package com.red_velvet_cake.dailytodo.presenter.updateTeam

import com.red_velvet_cake.dailytodo.data.TodoServiceImpl
import com.red_velvet_cake.dailytodo.domain.model.UpdateTeamTodoResponse

class UpdateTeamPresenter(private val iMainView: IMainView) {

    private val todoServiceImpl = TodoServiceImpl()

    fun updateTodoTeamStatus() {
        todoServiceImpl.updateTeamTodoStatus {
            iMainView.updateTodoTeam(it)
        }
    }


}

interface IMainView {
    fun updateTodoTeam(updateTeamTodoResponse: UpdateTeamTodoResponse)
}