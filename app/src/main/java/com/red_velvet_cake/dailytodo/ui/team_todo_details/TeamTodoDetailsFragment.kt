package com.red_velvet_cake.dailytodo.ui.team_todo_details

import android.os.Bundle
import android.view.LayoutInflater
import com.red_velvet_cake.dailytodo.data.model.TeamTodo
import android.view.ViewGroup
import com.red_velvet_cake.dailytodo.databinding.FragmentTeamTodoDetailsBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment

class TeamTodoDetailsFragment : BaseFragment<FragmentTeamTodoDetailsBinding>() {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTeamTodoDetailsBinding =
        FragmentTeamTodoDetailsBinding::inflate

    override fun setUp() {
        with(binding) {
            textViewTodoDetails.text = arguments?.getString("details")
            textViewTodoTitle.text = arguments?.getString("title")
            textViewAssigneName.text = arguments?.getString("assignee")
            textViewCreationDate.text = arguments?.getString("date")
            textViewTodoCreationTime.text = arguments?.getString("time")
        }
    }

    override fun addCallBacks() {}

    companion object {
        fun newInstance(teamTodo: TeamTodo): TeamTodoDetailsFragment {
            val bundle = Bundle()
            bundle.putString("title", teamTodo.title)
            bundle.putString("details", teamTodo.description)
            bundle.putString("assignee", teamTodo.assignee)
            bundle.putString("date", teamTodo.creationTime.substring(0..9))
            bundle.putString("time", teamTodo.creationTime.substring(11..15))

            val fragment = TeamTodoDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}