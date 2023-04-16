package com.red_velvet_cake.dailytodo.ui.team_todo

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.model.UpdateTeamTodoStatusResponse
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
        teamTodoPresenter.getAllTeamTodos()
        teamToDoAdapter = TeamToDoAdapter()
        teamTodoPresenter.updateTeamTodoStatus("8ae1a1fa-6e60-4eea-8e61-8ba5d5420f8d", 1)


    }

    override fun addCallBacks() {

    }

    override fun onGetAllTeamTodosSuccess(getAllTeamTodosResponse: GetAllTeamTodosResponse) {
        val itemTouchHelperCallback = ItemTeamTodoTouchHelperCallback(teamToDoAdapter)
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        teamToDoAdapter.submitList(getAllTeamTodosResponse.value)
        requireActivity().runOnUiThread {
            binding.teamTodoRecycler.adapter = teamToDoAdapter
            itemTouchHelper.attachToRecyclerView(binding.teamTodoRecycler)

        }
    }

    override fun onGetAllTeamTodosFailure(exception: IOException) {

    }

    override fun onUpdateTeamTodoStatusSuccess(updateTeamTodoStatusResponse: UpdateTeamTodoStatusResponse) {
        Log.d("TAG", "onUpdateTeamTodoStatusSuccess: ${updateTeamTodoStatusResponse.isSuccess} ")
    }

    override fun onUpdateTeamTodoStatusFailure(exception: IOException) {

    }


}