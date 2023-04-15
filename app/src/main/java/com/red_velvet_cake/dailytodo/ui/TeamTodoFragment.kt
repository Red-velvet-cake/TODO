package com.red_velvet_cake.dailytodo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.model.UpdatePersonalStatusResponse
import com.red_velvet_cake.dailytodo.databinding.FragmentTeamTodoBinding
import com.red_velvet_cake.dailytodo.presenter.IMainView
import com.red_velvet_cake.dailytodo.presenter.MainPresenter
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import okio.IOException

class TeamTodoFragment : BaseFragment<FragmentTeamTodoBinding>(), IMainView {
    private lateinit var teamToDoAdapter: TeamToDoAdapter
    private lateinit var mainPresenter: MainPresenter
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTeamTodoBinding =
        FragmentTeamTodoBinding::inflate

    override fun setUp() {
        mainPresenter = MainPresenter(this)
        mainPresenter.responseTeamTodo()
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