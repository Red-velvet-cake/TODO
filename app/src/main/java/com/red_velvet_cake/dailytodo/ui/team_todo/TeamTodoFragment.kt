package com.red_velvet_cake.dailytodo.ui.team_todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.model.TeamTodoResponse
import com.red_velvet_cake.dailytodo.data.model.UpdateTeamTodoStatusResponse
import com.red_velvet_cake.dailytodo.databinding.FragmentTeamTodoBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import okio.IOException

class TeamTodoFragment : BaseFragment<FragmentTeamTodoBinding>(), TeamTodo {
    private lateinit var teamToDoAdapter: TeamToDoAdapter
    private lateinit var teamTodoPresenter: TeamTodoPresenter
    private var selectedChip = CHIP_ALL_VALUE
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTeamTodoBinding =
        FragmentTeamTodoBinding::inflate

    override fun setUp() {
        initializePresenter()
        initializeAdapter()
    }

    override fun addCallBacks() {
        setChipClickListeners()
    }

    private fun initializePresenter() {
        teamTodoPresenter = TeamTodoPresenter(this)
        teamTodoPresenter.getAllTeamTodos()
    }

    private fun initializeAdapter() {
        teamToDoAdapter = TeamToDoAdapter()
        teamToDoAdapter.onUpdatedStatus = { id, updatedStatus ->
            teamTodoPresenter.updateTeamTodoStatus(id, updatedStatus)
        }
    }

    private fun setChipClickListeners() {
        binding.chipAll.setOnClickListener {
            selectedChip = CHIP_ALL_VALUE
            refreshTeamTodoList()
        }
        binding.chipTodo.setOnClickListener {
            selectedChip = CHIP_TODO_VALUE
            refreshTeamTodoList()
        }
        binding.chipDone.setOnClickListener {
            selectedChip = CHIP_DONE_VALUE
            refreshTeamTodoList()
        }
        binding.chipInProgress.setOnClickListener {
            selectedChip = CHIP_IN_PROGRESS_VALUE
            refreshTeamTodoList()
        }
    }

    override fun onGetAllTeamTodosSuccess(getAllTeamTodosResponse: GetAllTeamTodosResponse) {
        val itemTouchHelperCallback = ItemTeamTodoTouchHelperCallback(teamToDoAdapter)
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        val list =
            setTeamTodoListBasedOnSelectedChip(selectedChip, getAllTeamTodosResponse.value)
        requireActivity().runOnUiThread {
            teamToDoAdapter.submitList(list)
            binding.teamTodoRecycler.adapter = teamToDoAdapter
            itemTouchHelper.attachToRecyclerView(binding.teamTodoRecycler)
        }
    }

    private fun refreshTeamTodoList() {
        teamTodoPresenter.getAllTeamTodos()
    }

    private fun setTeamTodoListBasedOnSelectedChip(
        selectedChip: Int,
        teamTodoResponseList: List<TeamTodoResponse>
    ): List<TeamTodoResponse> =
        if (selectedChip == CHIP_ALL_VALUE) teamTodoResponseList else teamTodoResponseList.filter { it.status == selectedChip }

    override fun onGetAllTeamTodosFailure(exception: IOException) {
    }

    override fun onUpdateTeamTodoStatusSuccess(updateTeamTodoStatusResponse: UpdateTeamTodoStatusResponse) {
    }

    override fun onUpdateTeamTodoStatusFailure(exception: IOException) {
    }

    companion object {
        private const val CHIP_ALL_VALUE = 3
        private const val CHIP_TODO_VALUE = 0
        private const val CHIP_IN_PROGRESS_VALUE = 1
        private const val CHIP_DONE_VALUE = 2
    }

}