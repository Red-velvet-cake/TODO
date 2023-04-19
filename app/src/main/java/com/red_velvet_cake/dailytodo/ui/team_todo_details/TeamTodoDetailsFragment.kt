package com.red_velvet_cake.dailytodo.ui.team_todo_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.data.model.TeamTodo
import com.red_velvet_cake.dailytodo.databinding.FragmentTeamTodoDetailsBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import com.red_velvet_cake.dailytodo.utils.Constants
import com.red_velvet_cake.dailytodo.utils.TodoStatus
import com.red_velvet_cake.dailytodo.utils.navigateBack

class TeamTodoDetailsFragment : BaseFragment<FragmentTeamTodoDetailsBinding>(),
    TeamTodoDetailsView {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTeamTodoDetailsBinding
        get() = FragmentTeamTodoDetailsBinding::inflate

    private val presenter by lazy { TeamTodoStatusPresenter(this) }

    override fun setUp() {
        initSpinner()
        getTeamTodoFromArguments()
    }

    private fun initSpinner() {
        val teamTodoDetails: TeamTodo? = arguments?.getParcelable(KEY_DETAILS_PARAM)

        val status = listOf(
            getString(R.string.todo_status),
            getString(R.string.in_progress_status),
            getString(R.string.done_status)
        )
        val spinnerAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, status)
        spinnerAdapter.setDropDownViewResource(android.R.layout.select_dialog_multichoice)

        binding.spinnerTodoStatus.apply {
            adapter = spinnerAdapter
            onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        when (position) {
                            Constants.TODO -> {
                                presenter.setTodoStatus(TodoStatus.Todo, teamTodoDetails?.id!!)
                            }

                            Constants.IN_PROGRESS -> {
                                presenter.setTodoStatus(
                                    TodoStatus.InProgress,
                                    teamTodoDetails?.id!!
                                )
                            }

                            Constants.DONE -> {
                                presenter.setTodoStatus(TodoStatus.Done, teamTodoDetails?.id!!)
                            }
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        Toast.makeText(requireContext(), "Nothing Selected ", Toast.LENGTH_SHORT)
                            .show()
                    }

                }
        }
    }

    override fun addCallBacks() {
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().navigateBack()
        }
    }

    private fun getTeamTodoFromArguments() {
        val teamTodoDetails: TeamTodo? = arguments?.getParcelable(KEY_DETAILS_PARAM)
        with(binding) {
            textViewTodoTitle.text = teamTodoDetails?.title
            textViewTodoDescription.text = teamTodoDetails?.description
            textViewAssigneName.text = teamTodoDetails?.assignee
            textViewCreationDate.text = teamTodoDetails?.creationTime?.substring(0..9)
            textViewTodoCreationTime.text = teamTodoDetails?.creationTime?.substring(11..15)
        }
    }

    private fun makeToast(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }


    override fun showTodoStatusUpdatedFailure(errorMessage: String) {
        requireActivity().runOnUiThread {
            makeToast(errorMessage)
        }
    }

    companion object {
        private const val KEY_DETAILS_PARAM = "team todo details"

        fun newInstance(teamTodo: TeamTodo) = TeamTodoDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_DETAILS_PARAM, teamTodo)
            }
        }
    }
}
