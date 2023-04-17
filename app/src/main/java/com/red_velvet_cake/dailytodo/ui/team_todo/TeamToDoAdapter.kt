package com.red_velvet_cake.dailytodo.ui.team_todo

import android.view.LayoutInflater
import android.view.ViewGroup
import com.red_velvet_cake.dailytodo.data.model.TeamTodoResponse
import com.red_velvet_cake.dailytodo.databinding.LayoutTeamTodoItemBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseAdapter

class TeamToDoAdapter :
    BaseAdapter<TeamTodoResponse, LayoutTeamTodoItemBinding>() {

    var selectedChipAdapter = -1
    var onUpdatedStatus: (String, Int) -> Unit = { id, status -> }
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> LayoutTeamTodoItemBinding =
        LayoutTeamTodoItemBinding::inflate

    override fun bindItem(binding: LayoutTeamTodoItemBinding, item: TeamTodoResponse) {
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
    ): Boolean {
        return (getOldItems()[oldItemPosition] == newItems[newItemPosition])
    }

    fun swipedLeft(position: Int) {
        notifyItemRemoved(position)
        when (selectedChipAdapter) {
            0 -> {
                onUpdatedStatus(getOldItems()[position].id, 1)
            }
            1 -> {
                onUpdatedStatus(getOldItems()[position].id, 2)
            }
            2 -> {
                onUpdatedStatus(getOldItems()[position].id, 0)
            }
        }
    }

    fun swipedRight(position: Int) {
        notifyItemRemoved(position)
        when (selectedChipAdapter) {
            0 -> {
                onUpdatedStatus(getOldItems()[position].id, 2)
            }
            1 -> {
                onUpdatedStatus(getOldItems()[position].id, 0)
            }
            2 -> {
                onUpdatedStatus(getOldItems()[position].id, 1)
            }
        }
    }

    private fun extractDateAndTime(dateTimeString: String): Pair<String, String> {
        val parts = dateTimeString.split("T")
        return Pair(parts[0], parts[1].substring(0, 5))
    }

    fun setSelectedChip(selectedChip: Int) {
        this.selectedChipAdapter = selectedChip
    }

}
