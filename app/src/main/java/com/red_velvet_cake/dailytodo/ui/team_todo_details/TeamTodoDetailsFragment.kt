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
        val teamTodoDetails: TeamTodo? = arguments?.getParcelable(KEY)
        with(binding) {
            textViewTodoTitle.text = teamTodoDetails?.title
            textViewTodoDetails.text= teamTodoDetails?.description
            textViewAssigneName.text= teamTodoDetails?.assignee
            textViewCreationDate.text = teamTodoDetails?.creationTime?.substring(0..9)
            textViewTodoCreationTime.text = teamTodoDetails?.creationTime?.substring(11..15)

        }
    }

    override fun addCallBacks() {}

    companion object {
        const val KEY = "team_todo"
        fun newInstance(teamTodo: TeamTodo) =
            TeamTodoDetailsFragment.apply {
                val arguments = Bundle().apply {
                    putParcelable(KEY, teamTodo)
                }
            }
    }
}