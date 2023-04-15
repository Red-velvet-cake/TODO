package com.red_velvet_cake.dailytodo.ui.team_todo

import android.view.LayoutInflater
import android.view.ViewGroup
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.model.UpdatePersonalStatusResponse
import com.red_velvet_cake.dailytodo.databinding.FragmentTeamTodoBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import okio.IOException

class TeamTodoFragment : BaseFragment<FragmentTeamTodoBinding>(), TeamTodo {
    private lateinit var teamToDoAdapter: TeamToDoAdapter
    private lateinit var teamTodoPresenter: TeamTodoPresenter
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTeamTodoBinding =
        FragmentTeamTodoBinding::inflate

    override fun setUp() {
        teamTodoPresenter = TeamTodoPresenter(this)
        teamTodoPresenter.responseTeamTodo()
        teamToDoAdapter = TeamToDoAdapter()
    }

    override fun addCallBacks() {

    }

    override fun onGetAllTeamTodosSuccess(getAllTeamTodosResponse: GetAllTeamTodosResponse) {
        teamToDoAdapter.submitList(getAllTeamTodosResponse.value)
        requireActivity().runOnUiThread {
            binding.teamTodoRecycler.adapter = teamToDoAdapter
        }
    }

    override fun onGetAllTeamTodosFailure(exception: IOException) {

    }

    override fun onUpdatePersonalTodoStatusSuccess(updatePersonalStatusResponse: UpdatePersonalStatusResponse) {

    }

    override fun onUpdatePersonalTodoStatusFailure(exception: IOException) {

    }


}