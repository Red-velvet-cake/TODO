package com.red_velvet_cake.dailytodo.ui.personal_todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.data.model.PersonalTodo
import com.red_velvet_cake.dailytodo.databinding.FragmentPersonalTodoBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import com.red_velvet_cake.dailytodo.ui.personal_todo_details.PersonalTodoDetailsFragment
import com.red_velvet_cake.dailytodo.utils.navigateBack
import com.red_velvet_cake.dailytodo.utils.navigateTo

class PersonalTodoFragment : BaseFragment<FragmentPersonalTodoBinding>(), PersonalTodoView {

    private lateinit var personalToDoAdapter: PersonalTodoAdapter
    private lateinit var personalTodoPresenter: PersonalTodoPresenter
    private lateinit var filteredList:List<PersonalTodo>
    private var selectedChip = -1

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalTodoBinding =
        FragmentPersonalTodoBinding::inflate

    override fun setUp() {
        initializePresenter()
        initializeAdapter()
        selectedChip = CHIP_TODO_VALUE
        personalToDoAdapter.setSelectedChip(selectedChip)
        binding.chipTodo.isChecked = true
        binding.chipDone.setChipBackgroundColorResource(R.color.white)
        binding.chipInProgress.setChipBackgroundColorResource(R.color.white)
    }

    override fun addCallBacks() {
        binding.chipTodo.setOnClickListener {
            selectedChip = CHIP_TODO_VALUE
            refreshTeamTodoList()
            personalToDoAdapter.setSelectedChip(selectedChip)
            binding.chipDone.isChecked = false
            binding.chipInProgress.isChecked = false
            binding.chipDone.setChipBackgroundColorResource(R.color.white)
            binding.chipInProgress.setChipBackgroundColorResource(R.color.white)
            binding.chipTodo.setChipBackgroundColorResource(R.color.chip_background_color)
        }
        binding.chipDone.setOnClickListener {
            selectedChip = CHIP_DONE_VALUE
            refreshTeamTodoList()
            personalToDoAdapter.setSelectedChip(selectedChip)
            binding.chipTodo.isChecked = false
            binding.chipInProgress.isChecked = false
            binding.chipTodo.setChipBackgroundColorResource(R.color.white)
            binding.chipInProgress.setChipBackgroundColorResource(R.color.white)
            binding.chipDone.setChipBackgroundColorResource(R.color.chip_background_color)

        }
        binding.chipInProgress.setOnClickListener {
            selectedChip = CHIP_IN_PROGRESS_VALUE
            refreshTeamTodoList()
            personalToDoAdapter.setSelectedChip(selectedChip)
            binding.chipTodo.isChecked = false
            binding.chipDone.isChecked = false
            binding.chipDone.setChipBackgroundColorResource(R.color.white)
            binding.chipTodo.setChipBackgroundColorResource(R.color.white)
            binding.chipInProgress.setChipBackgroundColorResource(R.color.chip_background_color)
        }

        binding.appBar.setNavigationOnClickListener {
            requireActivity().navigateBack()
        }
    }

    private fun initializePresenter() {
        personalTodoPresenter = PersonalTodoPresenter(this)
        personalTodoPresenter.getAllTeamTodos()
    }


    private fun initializeAdapter() {
        personalToDoAdapter = PersonalTodoAdapter(::onUpdateStatus, ::onTodoClick)
    }

    private fun onTodoClick(personalTodo: PersonalTodo) {
        personalTodoPresenter.navigateToTodoDetails(personalTodo)
    }

    private fun onUpdateStatus(todoId: String, status: Int) {
        personalTodoPresenter.updateTeamTodoStatus(todoId, status)
    }

    private fun refreshTeamTodoList() {
        personalTodoPresenter.getAllTeamTodos()
    }

    private fun filterTodosList(
        selectedChip: Int,
        teamTodoList: List<PersonalTodo>
    ): List<PersonalTodo> =
        teamTodoList.filter { it.status == selectedChip }


    override fun showLoadTodosFailed() {

    }

    override fun showTodoUpdateFailMessage(errorMessage: String) {
        requireActivity().runOnUiThread {
            Toast.makeText(
                requireActivity(),
                errorMessage, Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun showEmptyTodoListState() {
        requireActivity().runOnUiThread {
            if (personalToDoAdapter.itemCount != 0) {
                binding.personalTodoRecycler.visibility = View.VISIBLE
                binding.emptyStateImageview.visibility = View.GONE
            } else {
                binding.personalTodoRecycler.visibility = View.GONE
                binding.emptyStateImageview.visibility = View.VISIBLE
            }
        }
    }

    override fun navigateToTodoDetails(personalTodo: PersonalTodo) {
        requireActivity().navigateTo(PersonalTodoDetailsFragment.newInstance(personalTodo))
    }

    override fun navigateBack() {
        requireActivity().navigateBack()
    }

    override fun showTodoList(todoList: List<PersonalTodo>) {
        val itemTouchHelperCallback = ItemPersonalTodoTouchHelperCallback(personalToDoAdapter)
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        filteredList =
            filterTodosList(selectedChip, todoList)
        requireActivity().runOnUiThread {
            personalToDoAdapter.submitList(filteredList)
            binding.personalTodoRecycler.adapter = personalToDoAdapter
            itemTouchHelper.attachToRecyclerView(binding.personalTodoRecycler)
        }
        showEmptyTodoListState()
    }

    companion object {
        private const val CHIP_TODO_VALUE = 0
        private const val CHIP_IN_PROGRESS_VALUE = 1
        private const val CHIP_DONE_VALUE = 2

        fun newInstance() = PersonalTodoFragment()
    }
}






