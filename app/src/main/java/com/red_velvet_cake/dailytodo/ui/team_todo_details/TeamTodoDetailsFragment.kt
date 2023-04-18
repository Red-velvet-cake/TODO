package com.red_velvet_cake.dailytodo.ui.team_todo_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.red_velvet_cake.dailytodo.data.model.TeamTodo
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.databinding.FragmentTeamTodoDetailsBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import com.red_velvet_cake.dailytodo.utils.Constants
import com.red_velvet_cake.dailytodo.utils.TodoStatus
import java.io.IOException

class TeamTodoDetailsFragment : BaseFragment<FragmentTeamTodoDetailsBinding>(),
    TeamTodoDetailsView {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTeamTodoDetailsBinding
        get() = FragmentTeamTodoDetailsBinding::inflate
    private lateinit var presenter: TeamTODOStatusPresenter

    override fun setUp() {
        initSpinner()
        setArgs()
    }

    private fun initSpinner() {
        val status = listOf("Todo", "In progress", "Done")
        val spinnerAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, status)
        spinnerAdapter.setDropDownViewResource(android.R.layout.select_dialog_multichoice)

        binding.spinnerTodoStatus.apply {
            adapter = spinnerAdapter
        }

    }

    override fun addCallBacks() {
        binding.spinnerTodoStatus.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        Constants.TODO -> {
                            presenter.setTodoStatus(TodoStatus.Todo, "details.id")
                        }

                        Constants.IN_PROGRESS -> {
                            presenter.setTodoStatus(TodoStatus.InProgress, "details.id")
                        }

                        Constants.DONE -> {
                            presenter.setTodoStatus(TodoStatus.Done, "details.id")
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Toast.makeText(requireContext(), "Nothing Selected ", Toast.LENGTH_SHORT).show()
                }

            }
    }

    private fun setArgs() {
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

    private fun setProgressBarVisibility(visibility: Boolean) {
        binding.progress.isVisible = visibility
    }

    override fun showTodoStatusUpdatedLoading(isLoading: Boolean) {
        requireActivity().runOnUiThread {
            setProgressBarVisibility(isLoading)
        }
    }

    override fun showTodoStatusUpdatedSuccessfully() {

        requireActivity().runOnUiThread {
            makeToast(getString(R.string.update_succeeded))
        }
    }

    override fun showTodoStatusUpdatedFailure(exception: IOException) {
        requireActivity().runOnUiThread {
            makeToast(exception.message.toString())
        }
    }

    companion object {
        private const val KEY_DETAILS_PARAM = "team todo details"

        fun newInstance(teamTodo: TeamTodo): TeamTodoDetailsFragment {
            val bundle = Bundle()
            bundle.putParcelable(KEY_DETAILS_PARAM, teamTodo)
            val fragment = TeamTodoDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
