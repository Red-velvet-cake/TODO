package com.red_velvet_cake.dailytodo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.red_velvet_cake.dailytodo.data.model.TeamTodo
import com.red_velvet_cake.dailytodo.databinding.LayoutTeamTodoItemBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseAdapter

class TeamToDoAdapter : BaseAdapter<TeamTodo, LayoutTeamTodoItemBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> LayoutTeamTodoItemBinding =
        LayoutTeamTodoItemBinding::inflate

    override fun bindItem(binding: LayoutTeamTodoItemBinding, item: TeamTodo) {
        val (date, time) = extractDateAndTime(item.creationTime)
        with(binding) {
            textviewTodoAssigneeName.text = item.assignee.toString()
            todoTitleTextview.text = item.title
            todoDescriptionTextview.text = item.description
            textviewTodoCreationDate.text = date
            textviewTodoCreationTime.text = time

        }
    }

    override fun <T> areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
        newItems: List<T>
    ) = getOldItems()[oldItemPosition].id == (newItems[newItemPosition] as TeamTodo).id

    private fun extractDateAndTime(dateTimeString: String): Pair<String, String> {
        val parts = dateTimeString.split("T")
        return Pair(parts[0], parts[1].substring(0, 5))
    }

}
