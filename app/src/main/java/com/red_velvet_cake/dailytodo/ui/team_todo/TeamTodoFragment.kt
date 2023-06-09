package com.red_velvet_cake.dailytodo.ui.team_todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import com.google.android.material.chip.Chip
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.data.model.TeamTodo
import com.red_velvet_cake.dailytodo.databinding.FragmentTeamTodoBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import com.red_velvet_cake.dailytodo.ui.team_todo_details.TeamTodoDetailsFragment
import com.red_velvet_cake.dailytodo.utils.navigateBack
import com.red_velvet_cake.dailytodo.utils.navigateTo

class TeamTodoFragment : BaseFragment<FragmentTeamTodoBinding>(), TeamTodoView {

    private lateinit var teamToDoAdapter: TeamTodoAdapter
    private lateinit var teamTodoPresenter: TeamTodoPresenter
    private lateinit var filteredList: List<TeamTodo>
    private var selectedChip = -1

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTeamTodoBinding =
        FragmentTeamTodoBinding::inflate

    override fun setUp() {
        initializePresenter()
        initializeAdapter()
        selectedChip = CHIP_TODO_VALUE
        teamToDoAdapter.setSelectedChip(selectedChip)
        binding.chipTodo.isChecked = true
        binding.chipDone.setChipBackgroundColorResource(R.color.white)
        binding.chipInProgress.setChipBackgroundColorResource(R.color.white)
    }

    override fun addCallBacks() {
        binding.chipTodo.setOnClickListener {
            setChipSelected(CHIP_TODO_VALUE, binding.chipTodo)
        }

        binding.chipDone.setOnClickListener {
            setChipSelected(CHIP_DONE_VALUE, binding.chipDone)
        }

        binding.chipInProgress.setOnClickListener {
            setChipSelected(CHIP_IN_PROGRESS_VALUE, binding.chipInProgress)
        }

        binding.appBar.setNavigationOnClickListener {
            requireActivity().navigateBack()
        }

        binding.buttonTryAgain.setOnClickListener {
            teamTodoPresenter.getAllTeamTodos()
        }
    }

    private fun initializePresenter() {
        teamTodoPresenter = TeamTodoPresenter(this)
        teamTodoPresenter.getAllTeamTodos()
    }


    private fun initializeAdapter() {
        teamToDoAdapter = TeamTodoAdapter(::onUpdateStatus, ::onTodoClicked)
    }

    private fun onUpdateStatus(todoId: String, status: Int) {
        teamTodoPresenter.updateTeamTodoStatus(todoId, status)
    }

    private fun refreshTeamTodoList() {
        teamTodoPresenter.getAllTeamTodos()
    }

    private fun filterTodosList(
        selectedChip: Int,
        teamTodoList: List<TeamTodo>
    ): List<TeamTodo> =
        teamTodoList.filter { it.status == selectedChip }

    override fun showLoadTodosFailed() {
        requireActivity().runOnUiThread {
            binding.progressBarLoadState.visibility = View.GONE
            binding.buttonTryAgain.visibility = View.VISIBLE
            binding.imageViewHasNoInternet.visibility = View.VISIBLE
        }
    }

    override fun showTodoUpdateFailMessage(errorMessage: String) {
        requireActivity().runOnUiThread {
            Toast.makeText(
                requireActivity(),
                errorMessage, Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun navigateToTodoDetails(todo: TeamTodo) {
        requireActivity().navigateTo(TeamTodoDetailsFragment.newInstance(todo))
    }

    override fun navigateBack() {
        requireActivity().navigateBack()
    }

    override fun showLoadStatus() {
        binding.teamTodoRecycler.visibility = View.GONE
        binding.emptyStateImageview.visibility = View.GONE
        binding.progressBarLoadState.visibility = View.VISIBLE
    }

    override fun showTodoList(todoList: List<TeamTodo>) {
        val itemTouchHelperCallback = ItemTeamTodoTouchHelperCallback(teamToDoAdapter)
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        filteredList = filterTodosList(selectedChip, todoList)
        requireActivity().runOnUiThread {
            binding.progressBarLoadState.visibility = View.GONE
            binding.teamTodoRecycler.visibility = View.VISIBLE
            binding.emptyStateImageview.visibility = View.GONE
            binding.buttonTryAgain.visibility = View.GONE
            binding.imageViewHasNoInternet.visibility = View.GONE
            teamToDoAdapter.submitList(filteredList)
            binding.teamTodoRecycler.adapter = teamToDoAdapter
            itemTouchHelper.attachToRecyclerView(binding.teamTodoRecycler)
            if (filteredList.isEmpty()) {
                binding.teamTodoRecycler.visibility = View.GONE
                binding.emptyStateImageview.visibility = View.VISIBLE
            }
        }
    }

    private fun setChipSelected(chipValue: Int, chipToSelect: Chip) {
        selectedChip = chipValue
        refreshTeamTodoList()
        teamToDoAdapter.setSelectedChip(selectedChip)
        binding.chipTodo.isChecked = false
        binding.chipDone.isChecked = false
        binding.chipInProgress.isChecked = false
        binding.chipTodo.setChipBackgroundColorResource(R.color.white)
        binding.chipDone.setChipBackgroundColorResource(R.color.white)
        binding.chipInProgress.setChipBackgroundColorResource(R.color.white)
        chipToSelect.isChecked = true
        chipToSelect.setChipBackgroundColorResource(R.color.chip_background_color)
    }

    private fun onTodoClicked(todo: TeamTodo) {
        teamTodoPresenter.navigateToTodoDetails(todo)
    }

    companion object {
        private const val CHIP_TODO_VALUE = 0
        private const val CHIP_IN_PROGRESS_VALUE = 1
        private const val CHIP_DONE_VALUE = 2

        fun newInstance() = TeamTodoFragment()
    }

}






