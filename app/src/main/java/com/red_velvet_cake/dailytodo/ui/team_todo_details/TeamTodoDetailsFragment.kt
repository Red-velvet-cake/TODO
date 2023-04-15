package com.red_velvet_cake.dailytodo.ui.team_todo_details

import android.view.LayoutInflater
import android.view.ViewGroup
import com.red_velvet_cake.dailytodo.databinding.FragmentTeamTodoDetailsBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment

class TeamTodoDetailsFragment : BaseFragment<FragmentTeamTodoDetailsBinding>() {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTeamTodoDetailsBinding =
        FragmentTeamTodoDetailsBinding::inflate

    override fun setUp() {
        // this variables just to testing
        val title = "Title todo "
        val details =
            "details todo , details todo , details todo , details todo" +
                    " , details todo , details todo , details todo , details todo , " +
                    "details todo , details todo , details todo , details todo , "
        val assignee = "Assignee Name "
        val creationDate = "15/04/2023"
        val creationTime = "05:30"

        binding.let {
            it.textViewTodoDetails.text = details
            it.textViewTodoTitle.text = title
            it.textViewAssigneName.text = assignee
            it.textViewCreationDate.text = creationDate
            it.textViewTodoCreationTime.text = creationTime
        }
    }

    override fun addCallBacks() {}
    
}